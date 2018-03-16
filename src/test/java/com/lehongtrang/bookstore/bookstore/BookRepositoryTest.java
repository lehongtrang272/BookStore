package com.lehongtrang.bookstore.bookstore;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import com.lehongtrang.bookstore.bookstore.domain.Book;
import com.lehongtrang.bookstore.bookstore.domain.BookRepository;
import com.lehongtrang.bookstore.bookstore.domain.Category;
import com.lehongtrang.bookstore.bookstore.domain.CategoryRepository;
import com.lehongtrang.bookstore.bookstore.domain.User;
import com.lehongtrang.bookstore.bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	@Autowired
	private BookRepository brepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@Autowired
	private UserRepository urepository; 
	
	
	@Test
	public void findByYearShouldReturnBook() {
		List<Book> books = brepository.findByYear(1960);
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getTitle()).isEqualTo("To kill a mocking bird");
	}
	@Test
	public void findByNameShouldReturnCategory() {
		List<Category> categories = crepository.findByName("Drama");
		assertThat(categories).hasSize(1);
	}
	@Test
	public void findByNameShouldReturnUser() {
		User user = urepository.findByUsername("user");
		assertThat(user).isNotNull();
	}
	@Test
	public void createBook() {
		Book book1 = new Book("Mickey Mouse and friends", "Disney", 1900, "fasdfdsfd", 21.2, new Category("Comic"));
		brepository.save(book1);
		assertThat(book1.getId()).isNotNull();
	}
	@Test
	public void createCategory() {
		Category category1 = new Category("Adventure");
		crepository.save(category1);
		assertThat(category1.getCategoryid()).isNotNull();
	}
	@Test
	public void createUser() {
		User user1 = new User("user1", "$2b$10$uo8s/Sy7xp4BKp5QZ5ePROBH4/EHL3zhNvqeSxQMQKPwdmxKR.4PK", "USER");
		urepository.save(user1);
		assertThat(user1.getId()).isNotNull();
	}

}
