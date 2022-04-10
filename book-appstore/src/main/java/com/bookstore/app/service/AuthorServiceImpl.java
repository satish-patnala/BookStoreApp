package com.bookstore.app.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.app.bean.AuthorDTO;
import com.bookstore.app.model.Author;
import com.bookstore.app.model.Book;
import com.bookstore.app.repository.AuthorRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public boolean saveAuthor(AuthorDTO authorDTO) {

		Author author = null;
		try {
			if (authorDTO.getId() != null && authorDTO.getId().compareTo(0l) > 0) {
				author = authorRepository.findById(authorDTO.getId()).orElse(null);
				if (author != null && author.getId() != null && author.getId().compareTo(0l) > 0
						&& authorDTO.getId().equals(author.getId())) {
					author.setBookCount(authorDTO.getBookCount());
					author.setGender(authorDTO.getGender());
					author.setAge(authorDTO.getAge());
					author.setNationality(authorDTO.getNationality());
					List<Book> books = authorDTO.getBooks().stream()
							.filter(bookDTO -> bookDTO.getBookName() != null && !bookDTO.getBookName().equals(""))
							.map(bookDTO -> new ModelMapper().map(bookDTO, Book.class)).collect(Collectors.toList());
					author.setBooks(books);
					authorRepository.save(author);
				}
			}
			if (author == null) {
				author = new Author();
				author.setBookCount(authorDTO.getBookCount());
				author.setGender(authorDTO.getGender());
				author.setAge(authorDTO.getAge());
				author.setNationality(authorDTO.getNationality());
				List<Book> books = authorDTO.getBooks().stream()
						.filter(bookDTO -> bookDTO.getBookName() != null && !bookDTO.getBookName().equals(""))
						.map(bookDTO -> new ModelMapper().map(bookDTO, Book.class)).collect(Collectors.toList());
				author.setBooks(books);
				authorRepository.save(author);
			}
			return true;
		} catch (Exception e) {
			log.info("Exception Occured while saving/updating Author Entity");
			return false;
		}
	}

	@Override
	public List<AuthorDTO> getAllAuthors() {
		ModelMapper mapper = new ModelMapper();
		List<Author> authors = authorRepository.findAll();
		if (authors != null && !authors.isEmpty()) {
			List<AuthorDTO> authorDTOs = authors.stream().map(author -> mapper.map(author, AuthorDTO.class))
					.collect(Collectors.toList());
			return authorDTOs;
		}
		return Collections.emptyList();
	}

}
