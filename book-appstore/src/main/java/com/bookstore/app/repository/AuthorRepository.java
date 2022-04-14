package com.bookstore.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookstore.app.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{
	
	@Query(value = "select a.author_id from author a,books_info b where a.author_id=b.author_id and b.book_name=:bookName ;",nativeQuery = true)
	public Long findOneAuthorByBookName(@Param("bookName") String bookName);
}
