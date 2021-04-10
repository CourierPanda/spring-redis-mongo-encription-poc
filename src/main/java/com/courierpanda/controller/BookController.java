package com.courierpanda.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.courierpanda.model.Book;
import com.courierpanda.repository.BookRepository;
import com.courierpanda.service.BookService;

@RestController
public class BookController {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BookService bookService;
	
	@PostMapping("/addBook")
	public String saveBook(@RequestBody Book book) {
		//Book book1 = bookService.setBookData(book);
		//template.opsForHash().put("book",book.getId(), book);
		bookRepository.save(book);
		return "Added book with id" + book.getId();
	}
	@GetMapping("/findBooks")
	public List<Book> getBooks(){
		return bookRepository.findAll();
	}
	@GetMapping("findBook/{id}")
	@Cacheable(value="findBook", key = "#id", unless = "#result.id == 103")
	public Optional<Book>  getBook(@PathVariable int id){
		System.out.println("from DB -------------");

		return bookRepository.findById(id);
	}
	@DeleteMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable int id) {
		bookRepository.deleteById(id);
		return "book deleted with id" + id;
	}

}
