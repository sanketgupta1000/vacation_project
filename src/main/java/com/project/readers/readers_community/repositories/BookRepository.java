package com.project.readers.readers_community.repositories;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.readers.readers_community.entities.Book;

public interface BookRepository extends JpaRepository<Book,Long> 
{
	 @Query(value="Select * from books where adminApproval=1",nativeQuery = true)
	 public List<Book> executeMyOwnQuery();
	 
	 
	 public List<Book> findByadminApproval(int approvalstatus);

}
