package com.project.readers.readers_community.repositories;

import com.project.readers.readers_community.entities.BookCopy;
import org.springframework.data.repository.CrudRepository;

public interface BookCopyRepository extends CrudRepository<BookCopy, Integer> {
}
