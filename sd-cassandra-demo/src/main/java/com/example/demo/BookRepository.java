package com.example.demo;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CassandraRepository<Book> {
  
  List<Book> findByAuthor(@Param("author") String a);

  @Query("select isbn, title, author from books where author='Kendall Crolius' allow filtering")
  List<Book> kendallsBooks();
  
}
