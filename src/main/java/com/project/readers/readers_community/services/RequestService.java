package com.project.readers.readers_community.services;

import com.project.readers.readers_community.DTOs.BorrowRequestDTO;
import com.project.readers.readers_community.DTOs.Mapper;
import com.project.readers.readers_community.entities.Book;
import com.project.readers.readers_community.entities.BookCopy;
import com.project.readers.readers_community.entities.BorrowRequest;
import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.enums.Approval;
import com.project.readers.readers_community.repositories.BookCopyRepository;
import com.project.readers.readers_community.repositories.BorrowRequestRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService
{

    private final BorrowRequestRepository borrowRequestRepository;
    private final EmailService emailService;
    private final BookCopyRepository bookCopyRepository;
    private final Mapper mapper;

    public RequestService(BorrowRequestRepository borrowRequestRepository, EmailService emailService, BookCopyRepository bookCopyRepository, Mapper mapper) {
        this.borrowRequestRepository = borrowRequestRepository;
        this.emailService = emailService;
        this.bookCopyRepository = bookCopyRepository;
        this.mapper = mapper;
    }

    // method to get all borrow requests of the current user
    // TODO: implement a query in a custom repository for this
    @Transactional
    public List<BorrowRequestDTO> getBorrowRequests(User user)
    {
        List<BorrowRequest> borrowRequests = new ArrayList<BorrowRequest>();

        for(Book b: user.getUploadedBooks())
        {
            for(BookCopy bookCopy: b.getBookCopies())
            {
                borrowRequests.addAll(bookCopy.getBorrowRequests());
            }
        }

        return borrowRequests
                .stream()
                .map(mapper::borrowRequestToBorrowRequestDTO)
                .toList();
    }

    // method to let the book owner approve a borrow request
    @Transactional
    public String approveBorrowRequest(int borrowRequestId, User user)
    {

        Optional<BorrowRequest> borrowRequestOptional = borrowRequestRepository.findById(borrowRequestId);

        if(borrowRequestOptional.isEmpty())
        {
            // not found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Borrow Request Not Found");
        }

        BorrowRequest borrowRequest = borrowRequestOptional.get();

        // checking if it is a request on one of the current user's books
        if(!borrowRequest.getBookCopy().getBook().getOwner().equals(user))
        {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to approve this request");
        }

        // checking if already responded
        if(borrowRequest.getOwnerApproval()!=Approval.UNRESPONDED)
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Request is already responded to");
        }

        // checking if is already meant to be passed on to somebody else
        if(!borrowRequest.getBookCopy().getBorrower().equals(borrowRequest.getBookCopy().getBook().getOwner()))
        {
            // already meant to be passed on
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Borrower already assigned");
        }

        // now can approve
        borrowRequest.setOwnerApproval(Approval.APPROVED);

        // set requester as next borrower
        BookCopy bookCopy = borrowRequest.getBookCopy();
        bookCopy.setBorrower(borrowRequest.getRequester());

        // save
        borrowRequestRepository.save(borrowRequest);
        bookCopyRepository.save(bookCopy);

        // send emails

        // email to the requester
        emailService.sendEmail(
                borrowRequest.getRequester().getEmail(),
                "Borrow Request Approved",
                "Congratulations! Your borrow request on book "+borrowRequest.getBookCopy().getBook().getBookTitle()+" has been approved."
        );

        // email to the holder
        emailService.sendEmail(
                borrowRequest.getBookCopy().getHolder().getEmail(),
                "Instruction to forward your borrowed book",
                "You are instructed by the owner to pass on your borrowed book "+borrowRequest.getBookCopy().getBook().getBookTitle()+" to "+borrowRequest.getRequester().getFullName()
        );

        return "Borrow Request Approved";

    }

    // method to let the book owner reject a borrow request
    @Transactional
    public String rejectBorrowRequest(int borrowRequestId, User user)
    {
        Optional<BorrowRequest> borrowRequestOptional = borrowRequestRepository.findById(borrowRequestId);

        if(borrowRequestOptional.isEmpty())
        {
            // not found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Borrow Request Not Found");
        }

        BorrowRequest borrowRequest = borrowRequestOptional.get();

        // checking if it is a request on one of the current user's books
        if(!borrowRequest.getBookCopy().getBook().getOwner().equals(user))
        {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to approve this request");
        }

        // checking if already responded
        if(borrowRequest.getOwnerApproval()!=Approval.UNRESPONDED)
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Request is already responded to");
        }

        // can reject
        borrowRequest.setOwnerApproval(Approval.REJECTED);

        borrowRequestRepository.save(borrowRequest);

        // send email to requester
        emailService.sendEmail(
                borrowRequest.getRequester().getEmail(),
                "Borrow request rejected",
                "Sorry! Your borrow request for book "+borrowRequest.getBookCopy().getBook().getBookTitle()+" has been rejected."
        );

        return "Borrow Request Rejected";

    }
}
