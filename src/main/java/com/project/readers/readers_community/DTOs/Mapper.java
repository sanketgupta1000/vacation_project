package com.project.readers.readers_community.DTOs;

import com.project.readers.readers_community.entities.Book;
import com.project.readers.readers_community.entities.BookCopy;
import com.project.readers.readers_community.entities.BorrowRequest;
import com.project.readers.readers_community.entities.BookTransaction;
import com.project.readers.readers_community.entities.MemberApprovalRequest;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;

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

    public BorrowRequestDTO borrowRequestToBorrowRequestDTO(BorrowRequest borrowRequest)
    {
        return new BorrowRequestDTO(
                borrowRequest.getId(),
                borrowRequest.getBookCopy().getId(),
                borrowRequest.getBookCopy().getBook().getBookTitle(),
                borrowRequest.getRequester().getId(),
                borrowRequest.getRequester().getFullName(),
                borrowRequest.getOwnerApproval().toString(),
                borrowRequest.getBookCopy().getBorrower().equals(borrowRequest.getBookCopy().getBook().getOwner())
        );
    }

    public BookCopyDTO bookCopyToBookCopyDTO(BookCopy bookCopy)
    {
        return new BookCopyDTO(
                bookCopy.getId(),
                bookCopy.getBook().getId(),
                bookCopy.getBook().getBookTitle(),
                bookCopy.getHolder().getId(),
                bookCopy.getHolder().getFullName(),
                bookCopy.getBorrower().getId(),
                bookCopy.getBorrower().getFullName(),
                bookCopy.getBorrower().equals(bookCopy.getBook().getOwner())
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
        return new MemberApprovalRequestDTO(
                memberApprovalRequest.getId(),
                memberApprovalRequest.getMember().getId(),
                memberApprovalRequest.getMember().getFullName(),
                memberApprovalRequest.getMember().getEmail(),
                memberApprovalRequest.getMember().getReferrer().getId(),
                memberApprovalRequest.getMember().getReferrer().getFullName(),
                memberApprovalRequest.getMember().getReferrer().getEmail(),
                memberApprovalRequest.getReferrerApproval().name(),
                memberApprovalRequest.getAdminApproval().name()
        );
    }

}
