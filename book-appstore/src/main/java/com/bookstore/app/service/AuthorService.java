package com.bookstore.app.service;

import java.util.List;

import com.bookstore.app.bean.AuthorDTO;

public interface AuthorService {
	
	public boolean saveAuthor(AuthorDTO authorDTO);

	public List<AuthorDTO> getAllAuthors();

}
