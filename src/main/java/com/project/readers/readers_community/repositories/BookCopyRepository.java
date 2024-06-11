package com.project.readers.readers_community.repositories;

import com.project.readers.readers_community.entities.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {
}
