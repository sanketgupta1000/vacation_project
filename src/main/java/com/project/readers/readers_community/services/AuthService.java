package com.project.readers.readers_community.services;

import com.project.readers.readers_community.entities.MemberApprovalRequest;
import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.enums.Approval;
import com.project.readers.readers_community.enums.UserType;
import com.project.readers.readers_community.repositories.MemberApprovalRequestRepository;
import com.project.readers.readers_community.repositories.UserRepository;
import com.project.readers.readers_community.utilities.LoginRequest;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthService
{

    // repository
    private final UserRepository userRepository;
    private  final MemberApprovalRequestRepository memberApprovalRequestRepository;

    private final PasswordEncoder passwordEncoder;
    private final OtpService otpService;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    // DI
    public AuthService(UserRepository userRepository, MemberApprovalRequestRepository memberApprovalRequestRepository, PasswordEncoder passwordEncoder, OtpService otpService, EmailService emailService, AuthenticationManager authenticationManager, TokenService tokenService)
    {
        this.userRepository = userRepository;
        this.memberApprovalRequestRepository = memberApprovalRequestRepository;
        this.passwordEncoder = passwordEncoder;
        this.otpService = otpService;
        this.emailService = emailService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    // method to signup
    @Transactional
    public String signup(User user)
    {
        // first need to check if email already registered or not
        User user1 = userRepository.findByEmail(user.getEmail());

        if(user1 != null)
        {
            // email already registered
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }

        // check referrer
        if(user.getReferrer()!=null)
        {
            // referrer provided
            // get the referrer
            Optional<User> referrer = userRepository.findById(user.getReferrer().getId());

            if(referrer.isPresent())
            {
                user.setReferrer(referrer.get());
            }
            else
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Referrer not found");
            }
        }

        // set id to 0
        user.setId(0);
        // hash the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // set user type
        user.setUserType(UserType.MEMBER);

        // generating otp
        String otp = otpService.generateOtp(6);
        // setting in entity
        user.setOtp(otp);
        user.setOtpVerified(false);
        user.setMemberApprovalRequest(null);
        // save
        userRepository.save(user);

        // send otp
        emailService.sendEmail(user.getEmail(), "OTP for signup verification", "OTP for signup: "+otp);

        return "OTP sent to the entered email for verification.";
    }

    // to verify otp
    @Transactional
    public String verifyOtp(String email, String otp)
    {

        // get the user
        User user = userRepository.findByEmail(email);

        if (user==null)
        {
            // not registered
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not found.");
        }

        if(user.isOtpVerified())
        {
            // otp already verified
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "OTP is already verified.");
        }

        // now need to verify
        if(!otp.equals(user.getOtp()))
        {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "OTP is incorrect.");
        }

        // set otp verified
        user.setOtpVerified(true);

        // now need to create a new request for the user
        MemberApprovalRequest approvalRequest = new MemberApprovalRequest();
        approvalRequest.setId(0);
        approvalRequest.setMember(user);
        // check if any referrer
        if(user.getReferrer()!=null)
        {
            // referrer exists
            approvalRequest.setReferrerApproval(Approval.UNRESPONDED);
        }
        else
        {
            // referrer does not exist, save as approved by referrer
            approvalRequest.setReferrerApproval(Approval.APPROVED);
        }
        approvalRequest.setAdminApproval(Approval.UNRESPONDED);

        user.setMemberApprovalRequest(approvalRequest);

        // save
        userRepository.save(user);

        return "Profile sent for approval";
    }

    @Transactional
    public String approveFromReference(MemberApprovalRequest request, User currentUser)
    {

        User reference = request.getMember().getReferrer();
        if(!currentUser.getEmail().equals(reference.getEmail()))
        {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User does not have access to this member approval request");
        }

        if( request.getReferrerApproval() != Approval.UNRESPONDED )
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Member approval request has already been responded to");
        }

        request.setReferrerApproval(Approval.APPROVED);

        memberApprovalRequestRepository.save(request);

        String to = request.getMember().getEmail();
        String subject = "Member approval request status";
        String message = "Your member approval request has been approved from your reference's side. Please wait for the admin's response.";
        emailService.sendEmail(to, subject, message);

        return "Member approval request has been approved from the reference side";
    }

    @Transactional
    public String rejectFromReference(MemberApprovalRequest request, User currentUser)
    {

        User reference = request.getMember().getReferrer();
        if(!currentUser.getEmail().equals(reference.getEmail()))
        {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User does not have access to this member approval request");
        }

        if( request.getReferrerApproval() != Approval.UNRESPONDED )
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Member approval request has already been responded to");
        }

        request.setReferrerApproval(Approval.REJECTED);

        memberApprovalRequestRepository.save(request);

        String to = request.getMember().getEmail();
        String subject = "Member approval request status";
        String message = "Your member approval request has been rejected from your reference's side. Please wait for the admin's response.";
        emailService.sendEmail(to, subject, message);

        return "Member approval request has been rejected from the reference side";
    }

    @Transactional
    public String approveFromAdmin(MemberApprovalRequest request)
    {

        if( request.getAdminApproval() != Approval.UNRESPONDED )
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Member approval request has already been responded to");
        }

        request.setAdminApproval(Approval.APPROVED);

        memberApprovalRequestRepository.save(request);

        String to = request.getMember().getEmail();
        String subject = "Member approval request status";
        String message = "Congratulations! Your member approval request has been approved by the admin. Please login to the site with the email and password used at the time of registration.";
        emailService.sendEmail(to, subject, message);

        return "Member approval request has been approved from the admin side";
    }

    @Transactional
    public String rejectFromAdmin(MemberApprovalRequest request)
    {


        if( request.getAdminApproval() != Approval.UNRESPONDED )
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Member approval request has already been responded to");
        }

        request.setAdminApproval(Approval.REJECTED);

        memberApprovalRequestRepository.save(request);

        String to = request.getMember().getEmail();
        String subject = "Member approval request status";
        String message = "Sorry, user! Your member approval request has been rejected by the admin. All your personal details has been removed from the site.";
        emailService.sendEmail(to, subject, message);

        return "Member approval request has been rejected from the admin side";
    }


    // method to send otp again for signup
    @Transactional
    public String sendOtp(String email)
    {
    	 User user1 = userRepository.findByEmail(email);

         // checking if user exists
         if(user1==null)
         {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found");
         }

         // checking if otp already verified
        if(user1.isOtpVerified())
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Member is already verified");
        }
    	 
    	 //generating otp
    	 String otp = otpService.generateOtp(6);
         // setting in entity
         user1.setOtp(otp);
         user1.setOtpVerified(false);
         user1.setMemberApprovalRequest(null);
         // save
         userRepository.save(user1);

         // send otp
         emailService.sendEmail(user1.getEmail(), "OTP for signup verification", "OTP for signup: "+otp);

         return "OTP sent to the entered email for verification.";
     
    }

    // login
    @Transactional
    public String login(LoginRequest loginRequest)
    {

        // doing custom checks
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if(user==null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found");
        }

        if(user.getUserType()!=UserType.ADMIN)
        {
            if (!user.isOtpVerified()) {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Please verify your otp before login");
            }

            if ((user.getMemberApprovalRequest().getAdminApproval() != Approval.APPROVED)) {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Your membersip is not yet approved by admin");
            }
        }
    	Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));

        return tokenService.generateToken(authentication);
    	
    }
}
