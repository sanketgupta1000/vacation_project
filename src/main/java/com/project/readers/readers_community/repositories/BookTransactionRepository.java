package com.project.readers.readers_community.repositories;

import com.project.readers.readers_community.entities.BookTransaction;
import com.project.readers.readers_community.entities.BookCopy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface BookTransactionRepository extends CrudRepository<BookTransaction, Long> {
}
