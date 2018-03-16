package com.lehongtrang.bookstore.bookstore;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lehongtrang.bookstore.bookstore.web.BookController;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class BookstoreApplicationTests {
	@Autowired
	private BookController bookcontroller;
	

	@Test
	public void contextLoads() throws Exception {
		assertThat(bookcontroller).isNotNull();
		
	}

}
