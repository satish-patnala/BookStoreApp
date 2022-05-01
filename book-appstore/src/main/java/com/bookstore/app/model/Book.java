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
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "books_info")
@DynamicUpdate
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long id;
	@Column(name = "book_name")
	private String bookName;
	@Column(name = "book_genre")
	private String bookGenre;
	@Column(name = "latest_edition")
	private String latestEdition;
	@Column(name = "active")
	private String isActive;
	@Column(name = "inserted_date")
	private Date insertedDate;
	@Column(name = "updated_date")
	private Date updatedDate;
	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "author_id")
	private Author author;

}
