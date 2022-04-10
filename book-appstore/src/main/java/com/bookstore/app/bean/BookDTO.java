package com.bookstore.app.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {

	private Long id;
	private Long authorId;
	private String bookName;
	private String author;
	private String genre;
}
