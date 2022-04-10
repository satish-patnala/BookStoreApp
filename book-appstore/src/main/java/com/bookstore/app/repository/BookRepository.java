package com.bookstore.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.app.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>,BookRepositoryCustom{

}
