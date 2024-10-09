package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Author;

/**
 * @author oktay
 *
 */
public interface AuthorRepository extends CrudRepository<Author, Integer>{

}
