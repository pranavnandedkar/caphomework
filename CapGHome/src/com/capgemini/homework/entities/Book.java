package com.capgemini.homework.entities;

public class Book {

	String id;
	String author;
	String edition;
	String price;
	String isbn;
	String available;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	/**
	 * @return the available
	 */
	public String getAvailable() {
		return available;
	}
	/**
	 * @param available the available to set
	 */
	public void setAvailable(String available) {
		this.available = available;
	}
}
