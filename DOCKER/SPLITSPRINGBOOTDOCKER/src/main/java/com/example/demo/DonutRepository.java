package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DonutRepository extends CrudRepository<Donut, Long> {
    List<Donut> findByNameIgnoreCase(String name);
}