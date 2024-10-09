package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Book;

/**
 * @author oktay
 *
 */
public interface BookService {
	
	public void addBook(Book book);
	
	public Book getBook(Integer bookId);
	
	public Book updateBook(Integer BookId,Book book);

	public void deleteBook(Integer bookId);

	public List<Book> getAllBooks();
}
