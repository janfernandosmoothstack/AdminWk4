package com.lms.LMSAdmin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.LMSAdmin.dao.BookDao;
import com.lms.LMSAdmin.pojo.Book;

@Component
public class BookService {
	
	@Autowired
	BookDao bookDao;
	
	//Insert record
	public Book insertBook(Book book) {
		return bookDao.save(book);
	}
	
	//Update record
	public Book updateBook(Book book) {
		return bookDao.save(book);
	}
	
	//Delete record
	public void deleteBook(Integer bookId) {
		bookDao.deleteById(bookId);;
	}
	
	//Get one book
	public Optional<Book> getBookById(Integer bookId){
		return bookDao.findById(bookId);
	}
	
	//Get all records
	public List<Book> getAllBooks() {
		return bookDao.findAll();
	}
	
	//Validate Id
	public boolean ifExists(Integer bookId) {
		List<Book> list = bookDao.findAll();
		
		boolean exists = list.stream()
				.anyMatch(id -> id.getBookId().equals(bookId));
	
		return exists;
	}
}
