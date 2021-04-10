package com.courierpanda.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.courierpanda.model.Book;

public interface BookRepository extends MongoRepository<Book, Integer>{


}
