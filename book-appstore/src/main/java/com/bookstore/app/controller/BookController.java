package com.bookstore.app.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.app.bean.BookDTO;
import com.bookstore.app.bean.GenericResponse;
import com.bookstore.app.service.BookService;

@RestController
@RequestMapping("/v1/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("/saveOrUpdateBook")
	public ResponseEntity<GenericResponse> saveOrUpdateBook(@RequestBody BookDTO book) {
		GenericResponse response = new GenericResponse();
		if (book != null && book.getBookName() != null && !book.getBookName().equals("")) {
			boolean isAdded = bookService.saveBook(book);
			if (isAdded) {
				response.setStatus("Success");
				response.setStatusCode("200");
			} else {
				response.setStatus("failed");
				response.setStatusCode("500");
			}

		}
		return ResponseEntity.ok(response);

	}

	@GetMapping("/getAllBooks")
	public ResponseEntity<List<BookDTO>> getAllBooks() {
		List<BookDTO> books = bookService.getAllBooks();
		if (books != null && !books.isEmpty()) {
			return ResponseEntity.ok(books);
		}
		return ResponseEntity.ok(Collections.emptyList());
	}

	@PostMapping("/deleteBook/{bookId}")
	public ResponseEntity<GenericResponse> deleteBook(@PathVariable("bookId") Long bookId) {
		GenericResponse response = new GenericResponse();
		if (bookId != null && bookId.compareTo(0l) > 0) {
			boolean isDeleted = bookService.deleteBook(bookId);
			if (isDeleted) {
				response.setStatus("Success");
				response.setStatusCode("200");
			} else {
				response.setStatus("failed");
				response.setStatusCode("500");
			}
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping("/lookUpBook/{bookId}")
	public ResponseEntity<BookDTO> getBookDetailsById(@PathVariable Long bookId) {
		BookDTO book = bookService.getBookDetailsById(bookId);
		if (book != null && book.getId().compareTo(0l) > 0) {
			return ResponseEntity.ok(book);
		}
		return new ResponseEntity<BookDTO>(HttpStatus.NOT_FOUND);
	}

}
