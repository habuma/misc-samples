package com.habuma.myapp;

import org.springframework.data.repository.CrudRepository;

public interface PeopleRepository extends CrudRepository<Person, Long> {

}
