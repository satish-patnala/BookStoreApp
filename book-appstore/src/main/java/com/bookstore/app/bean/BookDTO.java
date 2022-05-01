package com.bookstore.app.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {

	private Long id;
	private Long authorId;
	private String bookName;
	@JsonIgnore
	private AuthorDTO author;
	private String genre;
	private String latestEdition;
	private String isActive;
}
