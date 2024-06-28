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
                book.getCoverPhotoURL(),
                book.getAdminApproval().toString(),
                book.getOwner().getId(),
                book.getOwner().getFullName(),
                book.getOwner().getEmail(),
                book.getOwner().getProfilePhotoURL()
        );
    }
    public UserDTO userToUserDTO(User user)
    {
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd-MM-yyyy");
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
        dayFormat.format(user.getDateOfBirth()),
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
        referrerProfilePhotoURL);
    }

    public BorrowRequestDTO borrowRequestToBorrowRequestDTO(BorrowRequest borrowRequest)
    {
        return new BorrowRequestDTO(
                borrowRequest.getId(),
                borrowRequest.getBookCopy().getId(),
                borrowRequest.getBookCopy().getBook().getBookTitle(),
                borrowRequest.getBookCopy().getBook().getCoverPhotoURL(),
                borrowRequest.getRequester().getId(),
                borrowRequest.getRequester().getFullName(),
                borrowRequest.getRequester().getEmail(),
                borrowRequest.getRequester().getProfilePhotoURL(),
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

		if(memberApprovalRequest.getMember().getReferrer()!=null)
		{
			referrerId = memberApprovalRequest.getMember().getReferrer().getId();
			referrerName = memberApprovalRequest.getMember().getReferrer().getFullName();
			referrerEmail = memberApprovalRequest.getMember().getReferrer().getEmail();
            referrerProfilePhotoURL = memberApprovalRequest.getMember().getReferrer().getProfilePhotoURL();
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
            book.getCoverPhotoURL(),
            book.getAdminApproval().toString(),
            book.getOwner().getId(),
            book.getOwner().getFullName(),
            book.getOwner().getEmail(),
            book.getOwner().getProfilePhotoURL(),
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
