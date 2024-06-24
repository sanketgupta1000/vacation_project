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
                book.getAdminApproval().toString(),
                book.getOwner().getId(),
                book.getOwner().getFullName()
        );
    }
    public UserDTO userToUserDTO(User user)
    {
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd-MM-yyyy");
        int referrerId = 0;
    	  String referrerName=null;
        String referrerEmail=null;

        if(user.getReferrer()!=null)
        {
                referrerId = user.getReferrer().getId();
          referrerName = user.getReferrer().getFullName();
                referrerEmail = user.getReferrer().getEmail();
        }

        return new UserDTO(
            user.getId(),
            user.getEmail(),
            user.getFullName(),
            user.getPhoneNumber(),
            user.getUserType(),
            referrerId,
            referrerName,
            referrerEmail,
            user.getAddress().getHouseNo(),
            user.getAddress().getStreet(),
            user.getAddress().getLandmark(),
            user.getAddress().getCity(),
            user.getAddress().getState(),
            user.getAddress().getCountry(),
            dayFormat.format(user.getDateOfBirth())
            );
    }

    public BorrowRequestDTO borrowRequestToBorrowRequestDTO(BorrowRequest borrowRequest)
    {
        return new BorrowRequestDTO(
                borrowRequest.getId(),
                borrowRequest.getBookCopy().getId(),
                borrowRequest.getBookCopy().getBook().getBookTitle(),
                borrowRequest.getRequester().getId(),
                borrowRequest.getRequester().getFullName(),
                borrowRequest.getStatus().toString()
        );
    }

    public BookCopyDTO bookCopyToBookCopyDTO(BookCopy bookCopy, boolean canCurrentUserRequest, boolean canHandover)
    {
        return new BookCopyDTO(
                bookCopy.getId(),
                bookCopy.getBook().getId(),
                bookCopy.getBook().getBookTitle(),
                bookCopy.getHolder().getId(),
                bookCopy.getHolder().getFullName(),
                bookCopy.getBorrower().getId(),
                bookCopy.getBorrower().getFullName(),
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
                bookTransaction.getBookGiver().getId(),
                bookTransaction.getBookGiver().getFullName(),
                bookTransaction.getBookGiver().getEmail(),
                bookTransaction.getBookReceiver().getId(),
                bookTransaction.getBookReceiver().getFullName(),
                bookTransaction.getBookReceiver().getEmail(),
                dayFormat.format(bookTransaction.getTransactionDateTime()),
                timeFormat.format(bookTransaction.getTransactionDateTime())
        );
    }

    public MemberApprovalRequestDTO memberApprovalRequestToMemberApprovalRequestDTO(MemberApprovalRequest memberApprovalRequest)
    {
		Integer referrerId = null;
		String referrerName = null;
		String referrerEmail = null;

		if(memberApprovalRequest.getMember().getReferrer()!=null)
		{
			referrerId = memberApprovalRequest.getMember().getReferrer().getId();
			referrerName = memberApprovalRequest.getMember().getReferrer().getFullName();
			referrerEmail = memberApprovalRequest.getMember().getReferrer().getEmail();
		}

        return new MemberApprovalRequestDTO(
                memberApprovalRequest.getId(),
                memberApprovalRequest.getMember().getId(),
                memberApprovalRequest.getMember().getFullName(),
                memberApprovalRequest.getMember().getEmail(),
                referrerId,
				referrerName,
				referrerEmail,
                memberApprovalRequest.getReferrerApproval().name(),
                memberApprovalRequest.getAdminApproval().name()
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
            book.getAdminApproval().toString(),
            book.getOwner().getId(),
            book.getOwner().getFullName(),
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
                bookCopy.getHolder().getId(),
                bookCopy.getHolder().getFullName(),
                bookCopy.getBorrower().getId(),
                bookCopy.getBorrower().getFullName(),
                canRequest,
                showTransactions ? bookTransactions.stream().map(this::bookTransactionToBookTransactionDTO).toList() : null,
                canHandover
        );

    }

    public MemberSearchDTO userToMemberSearchDTO(User user)
    {
        return new MemberSearchDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail()
        );
    }

    public  AuthDTO userToAuthDTO(User user)
    {
        return new AuthDTO(
                user.getId(),
                user.getUserType()
        );
    }
}
