package com.project.readers.readers_community.DTOs;

import com.project.readers.readers_community.entities.Book;
import com.project.readers.readers_community.entities.BookCopy;
import com.project.readers.readers_community.entities.BorrowRequest;
import org.springframework.stereotype.Component;

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

}
