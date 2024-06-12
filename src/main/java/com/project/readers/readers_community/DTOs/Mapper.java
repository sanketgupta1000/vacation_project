package com.project.readers.readers_community.DTOs;

import com.project.readers.readers_community.entities.Book;
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

}
