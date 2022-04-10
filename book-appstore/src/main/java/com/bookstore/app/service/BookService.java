package com.bookstore.app.service;

import java.util.List;

import com.bookstore.app.bean.BookDTO;

public interface BookService {
	
	public boolean saveBook(BookDTO book);
	
	public List<BookDTO> getAllBooks();

	public boolean deleteBook(Long bookId);

	public BookDTO getBookDetailsById(Long bookId);

}
