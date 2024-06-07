package com.project.readers.readers_community.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.readers.readers_community.entities.Book;

public interface BookRepository extends JpaRepository<Book,Long> 
{
	
}
