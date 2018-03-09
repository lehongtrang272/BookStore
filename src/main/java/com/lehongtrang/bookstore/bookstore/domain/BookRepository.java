package com.lehongtrang.bookstore.bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends CrudRepository<Book, Long> {
	List<Book> findByTitle(@Param("title") String title);

}
