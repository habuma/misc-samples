package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SdCassandraDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SdCassandraDemoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner loader(BookRepository repo) {
	  return new CommandLineRunner() {
      
      @Override
      public void run(String... args) throws Exception {
        repo.insert(new Book("0312152906", "Knitting with Dog Hair", "Kendall Crolius"));
        repo.insert(new Book("1594745250", "Crafting with Cat Hair", "Kaori Tsutaya"));
        repo.insert(new Book("1617292540", "Spring Boot in Action", "Craig Walls"));
      }
    };
	}
	
}
