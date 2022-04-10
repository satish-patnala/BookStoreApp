package com.bookstore.app.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.app.bean.BookDTO;
import com.bookstore.app.model.Author;
import com.bookstore.app.model.Book;
import com.bookstore.app.repository.AuthorRepository;
import com.bookstore.app.repository.BookRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public boolean saveBook(BookDTO bookDTO) {
		Book book = null;
		try {
			if (bookDTO.getId() != null && bookDTO.getId().compareTo(0l) > 0) {
				book = bookRepository.findById(bookDTO.getId()).orElse(null);
				if (book != null && book.getId() != null && book.getId().compareTo(0l) > 0
						&& bookDTO.getId().equals(book.getId())) {
					book.setBookName(bookDTO.getBookName());
					book.setBookAuthor(bookDTO.getAuthor());
					book.setBookGenre(bookDTO.getGenre());
					book.setUpdatedDate(new Date());
					bookRepository.save(book);
				}
			}
			if (book == null) {
				book = new Book();
				book.setBookName(bookDTO.getBookName());
				book.setBookAuthor(bookDTO.getAuthor());
				book.setBookGenre(bookDTO.getGenre());
				if (bookDTO.getAuthorId() != null && bookDTO.getAuthorId().compareTo(0l) > 0) {
					Author author = authorRepository.findById(bookDTO.getAuthorId()).orElse(null);
					if (author != null && author.getId().equals(bookDTO.getAuthorId())) {
						book.setAuthor(author);
					}
				}
				book.setInsertedDate(new Date());
				book.setUpdatedDate(new Date());
				bookRepository.save(book);
			}
			return true;
		} catch (Exception e) {
			log.info("Exception Occured while saving/updating book Entity");
			return false;
		}
	}

	@Override
	public List<BookDTO> getAllBooks() {
		List<Book> booksList = bookRepository.findAll();
		if (booksList != null && !booksList.isEmpty()) {
			ModelMapper mapper = new ModelMapper();
			List<BookDTO> books = booksList.stream().map(book -> mapper.map(book, BookDTO.class))
					.collect(Collectors.toList());
			return books;
		}
		return Collections.emptyList();
	}

	@Override
	public boolean deleteBook(Long bookId) {
		if (bookId != null && bookId.compareTo(0l) > 0) {
			Book book = bookRepository.findById(bookId).orElse(null);
			if (book != null && book.getId().equals(bookId)) {
				bookRepository.delete(book);
				return true;
			}
		}
		return false;
	}

	@Override
	public BookDTO getBookDetailsById(Long bookId) {
		ModelMapper mapper = new ModelMapper();
		BookDTO bookDTO = null;
		if (bookId != null && bookId.compareTo(0l) > 0) {
			Book book = bookRepository.findById(bookId).orElse(null);
			if (book != null && book.getId() > 0l) {
				bookDTO = mapper.map(book, BookDTO.class);
			}
		}
		return bookDTO;
	}
}
