package com.bookstore.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("com.bookstore.app.*")
@EnableJpaRepositories
@SpringBootApplication
public class BookAppstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookAppstoreApplication.class, args);
	}

}
