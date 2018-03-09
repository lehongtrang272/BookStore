package com.lehongtrang.bookstore.bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lehongtrang.bookstore.bookstore.domain.Book;
import com.lehongtrang.bookstore.bookstore.domain.BookRepository;
import com.lehongtrang.bookstore.bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository;
	@Autowired
	private CategoryRepository crepository;
	
	  @RequestMapping(value="/login")
	    public String login() {	
	        return "login";
	    }	
	//Listing all books
	@RequestMapping(value="/booklist", method = RequestMethod.GET)
	public String showAllBooks(Model model){
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	//Add new book
	@RequestMapping(value="/add")
	public String addBook(Model model){
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "/addbook";
	}
	//Save new book
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveBook(Book book){
		repository.save(book);
		return "redirect:booklist";
	}
	//Delete book
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model){
		repository.delete(bookId);
		return "redirect:../booklist";
	}
	//Edit Book
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long bookId, Model model){
		model.addAttribute("book", repository.findOne(bookId));
		model.addAttribute("categories", crepository.findAll());
		return "/editbook";
	}
	//Save edited book
	@RequestMapping(value="/edit/save", method = RequestMethod.POST)
	public String saveEditBook(Book book){
		repository.save(book);
		return "redirect:../booklist";
	}
	//RESTful service find all book
	@RequestMapping(value="books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest(){
		return (List<Book>) repository.findAll();
	}
	//RESTful service find book id
	@RequestMapping(value="/books/{id}", method = RequestMethod.GET)
	public @ResponseBody Book findBookById(@PathVariable("id") Long bookId ){
		return repository.findOne(bookId);
	}

}
