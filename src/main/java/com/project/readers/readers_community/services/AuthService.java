package com.project.readers.readers_community.services;

import com.project.readers.readers_community.entities.MemberApprovalRequest;
import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.enums.Approval;
import com.project.readers.readers_community.enums.UserType;
import com.project.readers.readers_community.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthService
{

    // repository
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtpService otpService;
    private final EmailService emailService;

    // DI
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, OtpService otpService, EmailService emailService)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.otpService = otpService;
        this.emailService = emailService;
    }

    // method to signup
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

    // method to send otp again for signup
    public String sendOtp(String email)
    {

    }
}
