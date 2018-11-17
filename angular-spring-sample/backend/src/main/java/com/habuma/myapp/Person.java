package com.habuma.myapp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force=true, access=AccessLevel.PRIVATE)
@Entity
public class Person {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private final String firstName;
  private final String lastName;
  
}
