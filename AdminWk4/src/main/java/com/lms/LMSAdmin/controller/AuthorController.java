package com.lms.LMSAdmin.controller;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LMSAdmin.pojo.Author;
import com.lms.LMSAdmin.service.AuthorService;

@RestController
@RequestMapping("/admin/authors")
@Produces({"application/xml", "application/json"})
@Consumes({"application/xml", "application/json"})
public class AuthorController {

	@Autowired
	AuthorService authorService;
	
	//Create a record
	@PostMapping("")
	public ResponseEntity<?> insertAuthor(@RequestBody Author author) {
		
		authorService.insertAuthor(author);
		return new ResponseEntity<Author>(author, HttpStatus.CREATED);
	}
	
	//Update a record
	@PutMapping("/{authorId}")
	public ResponseEntity<?> updateAuthor(@PathVariable Integer authorId, @RequestBody Author author) {
		
		boolean checkId = authorService.ifExists(authorId);
		
		if(checkId == true) {
			author.setAuthorId(authorId);
			authorService.updateAuthor(author);
			return new ResponseEntity<Author>(author, HttpStatus.OK);
		}else {
			return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Delete a record
	@DeleteMapping("/{authorId}")
	public ResponseEntity<?> deleteAuthor(@PathVariable Integer authorId) {
		
		boolean checkId = authorService.ifExists(authorId);
		
		if(checkId == true) {
			authorService.deleteAuthor(authorId);
			return new ResponseEntity<Author>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Get one record
	@GetMapping("/{authorId}")
	@ResponseStatus(code = HttpStatus.OK)
	public Optional<Author> getAuthorById(@PathVariable Integer authorId){
		return authorService.getAuthorById(authorId);
	}
	
	//Get all records
	@GetMapping("")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Author> getAllAuthors() {
		return authorService.getAllAuthors();
	}
}
