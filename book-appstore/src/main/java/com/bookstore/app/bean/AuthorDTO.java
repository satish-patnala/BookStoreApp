package com.bookstore.app.bean;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDTO {
	
	private Long id;
	private Long bookCount;
	private String gender;
	private Integer age;
	private String nationality;
	private List<BookDTO> books;
	

}
