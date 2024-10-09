package com.okta.spring.SpringBootOAuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootOAuthClient extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootOAuthClient.class, args);
    }

}
