package com.bookstore.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
