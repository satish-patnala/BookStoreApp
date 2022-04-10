package com.bookstore.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.app.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{
	
}
