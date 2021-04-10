package com.courierpanda.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.courierpanda.model.Photo;

public interface PhotoRepository extends MongoRepository<Photo, String>{

}
