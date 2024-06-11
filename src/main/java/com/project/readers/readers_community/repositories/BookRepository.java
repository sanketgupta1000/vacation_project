package com.project.readers.readers_community.repositories;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.readers.readers_community.entities.Book;
import com.project.readers.readers_community.enums.Approval;

public interface BookRepository extends JpaRepository<Book,Long> 
{
	List<Book> findByAdminApproval(Approval status);
}
