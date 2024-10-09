package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Course;

public interface CourseRepository extends CrudRepository<Course, Long>  {
    
	public List<Course> findByName(String name);
}

