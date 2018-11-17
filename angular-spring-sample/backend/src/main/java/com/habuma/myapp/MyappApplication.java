package com.habuma.myapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyappApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner dataLoader(PeopleRepository repo) {
	  return args -> {
	    repo.save(new Person("Craig", "Walls"));
      repo.save(new Person("Raju", "Gandhi"));
      repo.save(new Person("Venkat", "Subramaniam"));
      repo.save(new Person("Nate", "Schutta"));
      repo.save(new Person("Michael", "Carducci"));
      repo.save(new Person("Matt", "Stine"));
      repo.save(new Person("Daniel", "Hinojosa"));
	  };
	}
	
}
