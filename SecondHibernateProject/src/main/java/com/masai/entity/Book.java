package com.masai.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="book_table")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 40, nullable = false, unique = true)
	private String title;
	@Column(length = 40, nullable = false)
	private String author;
	@Column(nullable = false, precision=7, scale=2)  //DECIMAL(7,2)----> XXXXX.YY
	private BigDecimal price;
	@Column(name="publish_date")
	private LocalDate publishDate;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}

	public Book(String title, String author, BigDecimal price, LocalDate publishDate) {
		super();
		this.title = title;
		this.author = author;
		this.price = price;
		this.publishDate = publishDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", price=" + price + ", publishDate="
				+ publishDate + "\n";
	}
	
}

//table: book_table
//book_id int(11) primary key auto_increment,
//title varchar(40)  NOT NULL UNIQUE,
//author varchar(40) NOT NULL,
//price decimal(7,2),
//publish_date date
