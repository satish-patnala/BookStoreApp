package com.bookstore.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "books_info")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long id;
	@Column(name = "book_name")
	private String bookName;
	@Column(name = "author_name")
	private String bookAuthor;
	@Column(name = "inserted_date")
	private Date insertedDate;
	@Column(name = "updated_date")
	private Date updatedDate;
	@Column(name = "book_genre")
	private String bookGenre;
	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "author_id")
	private Author author ;

}
