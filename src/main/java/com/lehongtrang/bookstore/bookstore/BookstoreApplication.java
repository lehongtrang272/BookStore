package com.lehongtrang.bookstore.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lehongtrang.bookstore.bookstore.domain.Book;
import com.lehongtrang.bookstore.bookstore.domain.BookRepository;
import com.lehongtrang.bookstore.bookstore.domain.Category;
import com.lehongtrang.bookstore.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner BookDemoData(BookRepository brepository, CategoryRepository crepository) {

		return (arg) -> {
			log.info("save a couple of students");
			crepository.save(new Category("Drama"));
			crepository.save(new Category("Life"));
			crepository.save(new Category("Comic"));

			brepository.save(new Book("To kill a mocking bird", "Harper Lee", 1960, "9788931001990", 45.23,
					crepository.findByName("Drama").get(0)));
			brepository.save(new Book("Tuesday with Morrie", "Mitch Albom", 1997, "9781283998734", 24.45,
					crepository.findByName("Life").get(0)));
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());

			}
			;
		};

	}
}
