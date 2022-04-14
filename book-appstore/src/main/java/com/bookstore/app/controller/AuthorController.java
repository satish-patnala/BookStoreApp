package com.bookstore.app.controller;

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

import com.bookstore.app.bean.AuthorDTO;
import com.bookstore.app.bean.GenericResponse;
import com.bookstore.app.service.AuthorService;

@RestController
@RequestMapping("/v1")
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@PostMapping("/saveOrUpdateAuthor")
	public ResponseEntity<GenericResponse> saveOrUpdateAuthor(@RequestBody AuthorDTO authorDTO) {
		GenericResponse response = new GenericResponse();
		if (authorDTO != null) {
			boolean isSaved = authorService.saveAuthor(authorDTO);
			if (isSaved) {
				response.setStatus("success");
				response.setStatusCode("200");
			} else {
				response.setStatus("Failed");
				response.setStatusCode("500");
			}
			return ResponseEntity.ok(response);
		}
		return new ResponseEntity<GenericResponse>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/getAllAuthors")
	public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
		try {
			List<AuthorDTO> authors = authorService.getAllAuthors();
			return ResponseEntity.ok(authors);
		} catch (Exception e) {
			return new ResponseEntity<List<AuthorDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/deleteAuthor/{authorId}")
	public ResponseEntity<GenericResponse> deleteAuthor(@PathVariable("authorId") Long authorId) {
		GenericResponse response = new GenericResponse();
		try {
			boolean isDeleted = authorService.deleteAuthor(authorId);
			if (isDeleted) {
				response.setStatus("Success");
				response.setStatusCode("200");
				return ResponseEntity.ok(response);
			}
			response.setStatus("Failed to delete entity");
			response.setStatusCode("500");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setStatus("Failed due to Exception : " + e.getMessage());
			response.setStatusCode("500");
			return new ResponseEntity<GenericResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getAuthorDetailsByBookName/{bookName}")
	public ResponseEntity<AuthorDTO> getAuthorDetailsByBookName(@PathVariable String bookName) {
		AuthorDTO author = authorService.getAuthorByBookName(bookName);
		if(author!=null&&author.getId()!=null&&author.getId().compareTo(0l)>0 ) {
			return ResponseEntity.ok(author);
		}
		return ResponseEntity.ok(new AuthorDTO());
	}
}
