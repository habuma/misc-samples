package com.habuma.myapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {

  private final PeopleRepository repo;
  
  @GetMapping
  public Iterable<Person> people() {
    return repo.findAll();
  }
  
}
