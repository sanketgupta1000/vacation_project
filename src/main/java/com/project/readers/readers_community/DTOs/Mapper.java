package com.project.readers.readers_community.DTOs;

import com.project.readers.readers_community.entities.*;

import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

// to map entities to dtos
@Component
public class Mapper
{
    SimpleDateFormat dateFormat;
    SimpleDateFormat timeFormat;

    public Mapper() {
        this.dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        this.timeFormat = new SimpleDateFormat("HH:mm:ss");
    }

    public BookDTO bookToBookDTO(Book book)
    {
        return new BookDTO(
                book.getId(),
                book.getBookTitle(),
                book.getAuthorName(),
                book.getPageCount(),
                book.getQuantity(),
                book.getCategory().getId(),
                book.getCategory().getName(),
                book.getCoverPhotoURL(),
                book.getAdminApproval().toString(),
                book.getOwner().getId(),
                book.getOwner().getFullName(),
                book.getOwner().getEmail(),
                book.getOwner().getProfilePhotoURL(),
                dateFormat.format(book.getRequestDateTime()),
                timeFormat.format(book.getRequestDateTime())
        );
    }
    public UserDTO userToUserDTO(User user)
    {
        int referrerId = 0;
        String referrerName=null;
        String referrerEmail=null;
        String referrerProfilePhotoURL = null;

        if(user.getReferrer()!=null)
        {
            referrerId = user.getReferrer().getId();
            referrerName = user.getReferrer().getFullName();
            referrerEmail = user.getReferrer().getEmail();
            referrerProfilePhotoURL = user.getReferrer().getProfilePhotoURL();
        }

        return new UserDTO(
        user.getId(),
        user.getEmail(),
        user.getFullName(),
        user.getPhoneNumber(),
        user.getUserType(),
        dateFormat.format(user.getDateOfBirth()),
        user.getProfilePhotoURL(),
        user.getAddress().getHouseNo(),
        user.getAddress().getStreet(),
        user.getAddress().getLandmark(),
        user.getAddress().getCity(),
        user.getAddress().getState(),
        user.getAddress().getCountry(),
        referrerId,
        referrerName,
        referrerEmail,
        referrerProfilePhotoURL,
        dateFormat.format(user.getMemberApprovalRequest().getResponseDateTime()),
        timeFormat.format(user.getMemberApprovalRequest().getResponseDateTime())
        );
    }

    public BorrowRequestDTO borrowRequestToBorrowRequestDTO(BorrowRequest borrowRequest)
    {
        String requestDate = null;
        String requestTime = null;
        String responseDate = null;
        String responseTime = null;
        String receiveDate = null;
        String receiveTime = null;
        String returnDate = null;
        String returnTime = null;

        if(borrowRequest.getRequestDateTime() != null)
        {
            requestDate = dateFormat.format(borrowRequest.getRequestDateTime());
            requestTime = timeFormat.format(borrowRequest.getRequestDateTime());
        }
        if(borrowRequest.getResponseDateTime() != null)
        {
            responseDate = dateFormat.format(borrowRequest.getResponseDateTime());
            responseTime = timeFormat.format(borrowRequest.getResponseDateTime());
        }
        if(borrowRequest.getReceiveDateTime() != null)
        {
            receiveDate = dateFormat.format(borrowRequest.getReceiveDateTime());
            receiveTime = timeFormat.format(borrowRequest.getReceiveDateTime());
        }
        if(borrowRequest.getReturnDateTime() != null)
        {
            returnDate = dateFormat.format(borrowRequest.getReturnDateTime());
            returnTime = timeFormat.format(borrowRequest.getReturnDateTime());
        }

        return new BorrowRequestDTO(
                borrowRequest.getId(),
                borrowRequest.getBookCopy().getId(),
                borrowRequest.getBookCopy().getBook().getBookTitle(),
                borrowRequest.getBookCopy().getBook().getCoverPhotoURL(),
                borrowRequest.getRequester().getId(),
                borrowRequest.getRequester().getFullName(),
                borrowRequest.getRequester().getEmail(),
                borrowRequest.getRequester().getProfilePhotoURL(),
                requestDate,
                requestTime,
                responseDate,
                responseTime,
                receiveDate,
                receiveTime,
                returnDate,
                returnTime,
                borrowRequest.getStatus().toString()
        );
    }

    public BookCopyDTO bookCopyToBookCopyDTO(BookCopy bookCopy, boolean canCurrentUserRequest, boolean canHandover)
    {
        return new BookCopyDTO(
                bookCopy.getId(),
                bookCopy.getBook().getId(),
                bookCopy.getBook().getBookTitle(),
                bookCopy.getBook().getCoverPhotoURL(),
                dateFormat.format(bookCopy.getBook().getResponseDateTime()),
                timeFormat.format(bookCopy.getBook().getResponseDateTime()),
                bookCopy.getHolder().getId(),
                bookCopy.getHolder().getFullName(),
                bookCopy.getHolder().getEmail(),
                bookCopy.getHolder().getProfilePhotoURL(),
                bookCopy.getBorrower().getId(),
                bookCopy.getBorrower().getFullName(),
                bookCopy.getBorrower().getEmail(),
                bookCopy.getBorrower().getProfilePhotoURL(),
                canCurrentUserRequest,
                canHandover
        );
    }

    public BookTransactionDTO bookTransactionToBookTransactionDTO(BookTransaction bookTransaction)
    {
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd-MM-yyyy"); // Format for day
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss"); // Format for time

        return new BookTransactionDTO(
                bookTransaction.getId(),
                bookTransaction.getBookCopy().getId(),
                bookTransaction.getBookCopy().getBook().getId(),
                bookTransaction.getBookCopy().getBook().getBookTitle(),
                bookTransaction.getBookCopy().getBook().getCoverPhotoURL(),
                bookTransaction.getBookGiver().getId(),
                bookTransaction.getBookGiver().getFullName(),
                bookTransaction.getBookGiver().getEmail(),
                bookTransaction.getBookGiver().getProfilePhotoURL(),
                bookTransaction.getBookReceiver().getId(),
                bookTransaction.getBookReceiver().getFullName(),
                bookTransaction.getBookReceiver().getEmail(),
                bookTransaction.getBookReceiver().getProfilePhotoURL(),
                dayFormat.format(bookTransaction.getTransactionDateTime()),
                timeFormat.format(bookTransaction.getTransactionDateTime())
        );
    }

    public MemberApprovalRequestDTO memberApprovalRequestToMemberApprovalRequestDTO(MemberApprovalRequest memberApprovalRequest)
    {
		Integer referrerId = null;
		String referrerName = null;
		String referrerEmail = null;
        String referrerProfilePhotoURL = null;

        String responseDate = null;
        String responseTime = null;

		if(memberApprovalRequest.getMember().getReferrer()!=null)
		{
			referrerId = memberApprovalRequest.getMember().getReferrer().getId();
			referrerName = memberApprovalRequest.getMember().getReferrer().getFullName();
			referrerEmail = memberApprovalRequest.getMember().getReferrer().getEmail();
            referrerProfilePhotoURL = memberApprovalRequest.getMember().getReferrer().getProfilePhotoURL();
		}

        if(memberApprovalRequest.getResponseDateTime() != null)
        {
            responseDate = dateFormat.format(memberApprovalRequest.getResponseDateTime());
            responseTime = timeFormat.format(memberApprovalRequest.getResponseDateTime());
        }

        return new MemberApprovalRequestDTO(
                memberApprovalRequest.getId(),
                memberApprovalRequest.getMember().getId(),
                memberApprovalRequest.getMember().getFullName(),
                memberApprovalRequest.getMember().getEmail(),
                memberApprovalRequest.getMember().getPhoneNumber(),
                referrerId,
				referrerName,
				referrerEmail,
                referrerProfilePhotoURL,
                memberApprovalRequest.getReferrerApproval().name(),
                memberApprovalRequest.getAdminApproval().name(),
                dateFormat.format(memberApprovalRequest.getRequestDateTime()),
                timeFormat.format(memberApprovalRequest.getRequestDateTime()),
                responseDate,
                responseTime
        );
    }

    public CategoryDTO categoryToCategoryDTO(BookCategory bookCategory)
    {
        return new CategoryDTO(
                bookCategory.getId(),
                bookCategory.getName()
        );
    }

    public BookCopiesDTO bookToBookCopiesDTO(Book book, User currentUser)
    {

        // Check if the current user can request for a book copy
        boolean canCurrentUserRequest = (!book.getOwner().equals(currentUser))
                                        &&
                                        (currentUser.getCurrentBorrowRequest()==null);

        List<BookCopyDTO> bookCopyDTOList= book
            .getBookCopies()
            .stream()
            .map(bookCopy ->
                    bookCopyToBookCopyDTO(
                            bookCopy,
                            canCurrentUserRequest && (book.getOwner().equals(bookCopy.getBorrower())),
                            currentUser.equals(bookCopy.getHolder()) && !(currentUser.equals(bookCopy.getBorrower()))))
            .toList();

        return new BookCopiesDTO(
            book.getId(),
            book.getBookTitle(),
            book.getAuthorName(),
            book.getPageCount(),
            book.getQuantity(),
            book.getCategory().getId(),
            book.getCategory().getName(),
            book.getCoverPhotoURL(),
            book.getAdminApproval().toString(),
            book.getOwner().getId(),
            book.getOwner().getFullName(),
            book.getOwner().getEmail(),
            book.getOwner().getProfilePhotoURL(),
            dateFormat.format(book.getResponseDateTime()),
            timeFormat.format(book.getResponseDateTime()),
            bookCopyDTOList
        );

    }

    public BookTransactionsDTO bookCopyToBookTransactionsDTO(BookCopy bookCopy, boolean canRequest, boolean showTransactions, Comparator<BookTransaction> bookTransactionComparator, boolean canHandover)
    {

        List<BookTransaction> bookTransactions = null;

        if(showTransactions)
        {
            bookTransactions = bookCopy.getTransactions();
            bookTransactions.sort(bookTransactionComparator);
        }

        return new BookTransactionsDTO(
                bookCopy.getId(),
                bookCopy.getBook().getId(),
                bookCopy.getBook().getBookTitle(),
                bookCopy.getBook().getCoverPhotoURL(),
                dateFormat.format(bookCopy.getBook().getResponseDateTime()),
                timeFormat.format(bookCopy.getBook().getResponseDateTime()),
                bookCopy.getHolder().getId(),
                bookCopy.getHolder().getFullName(),
                bookCopy.getHolder().getEmail(),
                bookCopy.getHolder().getProfilePhotoURL(),
                bookCopy.getBorrower().getId(),
                bookCopy.getBorrower().getFullName(),
                bookCopy.getBorrower().getEmail(),
                bookCopy.getBorrower().getProfilePhotoURL(),
                canRequest,
                canHandover,
                showTransactions ? bookTransactions.stream().map(this::bookTransactionToBookTransactionDTO).toList() : null
        );

    }

    public MemberSearchDTO userToMemberSearchDTO(User user)
    {
        return new MemberSearchDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getProfilePhotoURL()
        );
    }

    public  AuthDTO userToAuthDTO(User user)
    {
        return new AuthDTO(
                user.getId(),
                user.getUserType(),
                user.getProfilePhotoURL()
        );
    }
}
