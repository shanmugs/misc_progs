package com.start;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.stripe.Stripe;

@SpringBootApplication
public class StripePaymentBeignningApplication {
	
	@PostConstruct
	public void setup()
	{
		 Stripe.apiKey = "enter your secret key";
	}

	public static void main(String[] args) {
		SpringApplication.run(StripePaymentBeignningApplication.class, args);
	}

}
