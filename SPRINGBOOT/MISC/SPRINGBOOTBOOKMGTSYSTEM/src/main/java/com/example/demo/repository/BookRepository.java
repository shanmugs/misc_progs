package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Book;

/**
 * @author oktay
 *
 */
public interface BookRepository extends CrudRepository<Book, Integer>{

}
