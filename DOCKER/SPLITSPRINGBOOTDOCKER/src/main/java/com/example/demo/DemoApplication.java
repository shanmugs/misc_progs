package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	DonutRepository donutRepository;

	DemoApplication(DonutRepository donutRepository) {
		this.donutRepository = donutRepository;
	}

	@PostConstruct
	public void initApplication() {
		if (donutRepository.count() > 1) return;
		donutRepository.save(new Donut("Chocolate", DonutToppings.NONE, 1.50, 10));
		donutRepository.save(new Donut("Maple", DonutToppings.MAPLE, 1.0, 5));
		donutRepository.save(new Donut("Glazed", DonutToppings.GLAZED, 0.75, 20));
	}
}
