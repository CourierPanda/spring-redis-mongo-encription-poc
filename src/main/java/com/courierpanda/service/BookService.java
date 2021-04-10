package com.courierpanda.service;

import org.springframework.stereotype.Service;

import com.courierpanda.model.Book;
@Service
public class BookService {
	
	public BookService() {
		
	}
	
	public Book setBookData(Book book) {
		Book book1 = new Book();
		book1.setId(book.getId());
		book1.setBookName(book.getBookName());
		book1.setAuthorName(book.getAuthorName());
		return book1;
	}

}
