package com.lms.LMSOrchestrator;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.lms.LMSAdmin.pojo.Author;
import com.lms.LMSAdmin.pojo.Book;
import com.lms.LMSAdmin.pojo.BookLoans;
import com.lms.LMSAdmin.pojo.Borrower;
import com.lms.LMSAdmin.pojo.LibraryBranch;
import com.lms.LMSAdmin.pojo.Publisher;
import com.lms.LMSAdmin.pojo.BookCopies;


@SpringBootApplication
@RestController
@RequestMapping(value = "/lms")
@Produces({"application/xml", "application/json"})
@Consumes({"application/xml", "application/json"})
public class LmsOrchestratorApplication {
	
	@Autowired
	RestTemplate restTemp;
	
	private final String adminUri = "http://localhost:8070/admin";
	
	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(LmsOrchestratorApplication.class, args);
	}
	
	
	/*
	 * Author Orchestrator
	 */
	
	//Create author
	@PostMapping("/admin/authors")
	public ResponseEntity<?> insertAuthor(@RequestHeader("Accept") String accept, 
			@RequestHeader("Content-Type") String contentType, @RequestBody Author author) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/authors", HttpMethod.POST, 
					new HttpEntity<Author>(author, headers), Author.class);
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Author>(e.getStatusCode());
		}
	}
	
	//Update author
	@PutMapping("/admin/authors/{authorId}")
	public ResponseEntity<?> updateAuthor(@RequestHeader("Accept") String accept, 
			@RequestHeader("Content-Type") String contentType, @PathVariable Integer authorId, @RequestBody Author author) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/authors/{authorId}", HttpMethod.PUT, 
					new HttpEntity<Author>(author, headers), Author.class, authorId);
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Author>(e.getStatusCode());
		}
	}
	
	//Delete author
	@DeleteMapping("/admin/authors/{authorId}")
	public ResponseEntity<?> deleteAuthor(@RequestHeader("Accept") String accept, 
			@RequestHeader("Content-Type") String contentType, @PathVariable Integer authorId) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/authors/{authorId}", HttpMethod.DELETE, 
					new HttpEntity<Author>(headers), Author.class, authorId);
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Author>(e.getStatusCode());
		}
	}
	
	//View one author
	@GetMapping("/admin/authors/{authorId}")
	public ResponseEntity<?> getAuthorById(@RequestHeader("Accept") String accept, 
			@RequestHeader("Content-Type") String contentType, @PathVariable Integer authorId) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/authors/{authorId}", HttpMethod.GET, 
					new HttpEntity<Author>(headers), Author.class, authorId);
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Author>(e.getStatusCode());
		}
	}

	//View all authors
	@GetMapping("/admin/authors")
	public ResponseEntity<Iterable<Author>> getAllAuthors(@RequestHeader("Accept") String accept) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);
		
		try {
		return restTemp.exchange(adminUri + "/authors", HttpMethod.GET, new HttpEntity<Object>(headers), 
				new ParameterizedTypeReference<Iterable<Author>>(){});
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Iterable<Author>>(e.getStatusCode());
		}
	}
	
	
	/*
	 * Book Orchestrator
	 */
	
	//Create book
	@PostMapping("/admin/books")
	public ResponseEntity<?> insertBook(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@RequestBody Book book) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/books", HttpMethod.POST, 
					new HttpEntity<Book>(book, headers), Book.class);
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Book>(e.getStatusCode());
		}
	}
	
	//Update book
	@PutMapping("/admin/books/{bookId}")
	public ResponseEntity<?> updateBook(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@PathVariable Integer bookId, @RequestBody Book book) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/books/{bookId}", HttpMethod.PUT, 
					new HttpEntity<Book>(book, headers), Book.class, bookId);
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Book>(e.getStatusCode());
		}
	}
	
	//Delete book
	@DeleteMapping("/admin/books/{bookId}")
	public ResponseEntity<?> deleteBook(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@PathVariable Integer bookId) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/books/{bookId}", HttpMethod.DELETE, 
					new HttpEntity<Object>(headers), Book.class, bookId);
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Book>(e.getStatusCode());
		}
	}
	
	//View one book
	@GetMapping("/admin/books/{bookId}")
	public ResponseEntity<?> getBookById(@RequestHeader("Accept") String accept, 
			@RequestHeader("Content-Type") String contentType, @PathVariable Integer bookId) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/books/{bookId}", HttpMethod.GET, 
					new HttpEntity<Book>(headers), Book.class, bookId);
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Book>(e.getStatusCode());
		}
	}
	
	//View all books
	@GetMapping("/admin/books")
	public ResponseEntity<Iterable<Book>> getAllBooks(@RequestHeader("Accept") String accept) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/books", HttpMethod.GET, new HttpEntity<Object>(headers), 
				new ParameterizedTypeReference<Iterable<Book>>(){});
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Iterable<Book>>(e.getStatusCode());
		}
	}
	
	
	/*
	 * Borrower Orchestrator
	 */
	
	//Create borrower
	@PostMapping("/admin/borrowers")
	public ResponseEntity<?> insertBorr(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@RequestBody Borrower borrower) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/borrowers", 
				HttpMethod.POST, new HttpEntity<Borrower>(borrower, headers), Borrower.class);
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Borrower>(e.getStatusCode());
		}
	}
	
	//Update borrower
	@PutMapping("/admin/borrowers/{cardNo}")
	public ResponseEntity<?> updateBorr(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@PathVariable Integer cardNo, @RequestBody Borrower borrower) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/borrowers/{cardNo}", HttpMethod.PUT, 
					new HttpEntity<Borrower>(borrower, headers), Borrower.class, cardNo);
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Borrower>(e.getStatusCode());
		}
	}
	
	//Delete borrower
	@DeleteMapping("/admin/borrowers/{cardNo}")
	public ResponseEntity<?> deleteBorr(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@PathVariable Integer cardNo) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/borrowers/{cardNo}", HttpMethod.DELETE, 
					new HttpEntity<Borrower>(headers), Borrower.class, cardNo);
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Borrower>(e.getStatusCode());
		}
	}
	
	//View one borrower
	@GetMapping("/admin/borrowers/{cardNo}")
	public ResponseEntity<?> getBorrById(@RequestHeader("Accept") String accept, 
			@RequestHeader("Content-Type") String contentType, @PathVariable Integer cardNo) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/borrowers/{cardNo}", HttpMethod.GET, 
					new HttpEntity<Borrower>(headers), Borrower.class, cardNo);
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Borrower>(e.getStatusCode());
		}
	}
	
	//View all borrower
	@GetMapping("/admin/borrowers")
	public ResponseEntity<Iterable<Borrower>> getAllBorrs(@RequestHeader("Accept") String accept) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/borrowers", HttpMethod.GET, new HttpEntity<Object>(headers), 
				new ParameterizedTypeReference<Iterable<Borrower>>(){});
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Iterable<Borrower>>(e.getStatusCode());
		}
	}
	
	
	/*
	 * Library Branch Orchestrator
	 */
	
	//Create branch
	@PostMapping("/admin/branches")
	public ResponseEntity<?> insertBranch(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@RequestBody LibraryBranch branch) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/branches", 
				HttpMethod.POST, new HttpEntity<LibraryBranch>(branch, headers), LibraryBranch.class);
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<LibraryBranch>(e.getStatusCode());
		}
	}
	
	//Update branch
	@PutMapping("/admin/branches/{branchId}")
	public ResponseEntity<?> updateBranch(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@PathVariable Integer branchId, @RequestBody LibraryBranch branch) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/branches/{branchId}", HttpMethod.PUT, 
					new HttpEntity<LibraryBranch>(branch, headers), LibraryBranch.class, branchId);
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<LibraryBranch>(e.getStatusCode());
		}
	}
	
	//Delete branch
	@DeleteMapping("/admin/branches/{branchId}")
	public ResponseEntity<?> deleteBranch(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@PathVariable Integer branchId) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/branches/{branchId}", HttpMethod.DELETE, 
					new HttpEntity<LibraryBranch>(headers), LibraryBranch.class, branchId);
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<LibraryBranch>(e.getStatusCode());
		}
	}
	
	//View one branch
	@GetMapping("/admin/branches/{branchId}")
	public ResponseEntity<?> getBranchById(@RequestHeader("Accept") String accept, 
			@RequestHeader("Content-Type") String contentType, @PathVariable Integer branchId) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/branches/{branchId}", HttpMethod.GET, 
					new HttpEntity<LibraryBranch>(headers), LibraryBranch.class, branchId);
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<LibraryBranch>(e.getStatusCode());
		}
	}
	
	//View all branches
	@GetMapping("/admin/branches")
	public ResponseEntity<Iterable<LibraryBranch>> getAvailableBranch(@RequestHeader("Accept") String accept) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/branches", HttpMethod.GET, 
				new HttpEntity<Object>(headers), new ParameterizedTypeReference<Iterable<LibraryBranch>>(){});
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Iterable<LibraryBranch>>(e.getStatusCode());
		}
	}
	
	
	
	/*
	 * Publisher Orchestrator
	 */
	
	//Create pub
	@PostMapping("/admin/publishers")
	public ResponseEntity<?> insertPub(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@RequestBody Publisher pub) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/publishers", 
				HttpMethod.POST, new HttpEntity<Publisher>(pub, headers), Publisher.class);
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Publisher>(e.getStatusCode());
		}
	}
	
	//Update pub
	@PutMapping("/admin/publishers/{publisherId}")
	public ResponseEntity<?> updatePub(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@PathVariable Integer publisherId, @RequestBody Publisher pub) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/publishers/{publisherId}", HttpMethod.PUT, 
					new HttpEntity<Publisher>(pub, headers), Publisher.class, publisherId);
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Publisher>(e.getStatusCode());
		}
	}
	
	//Delete pub
	@DeleteMapping(value = "/admin/publishers/{publisherId}")
	public ResponseEntity<?> deletePub(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@PathVariable Integer publisherId) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/publishers/{publisherId}", HttpMethod.DELETE, 
					new HttpEntity<Publisher>(headers), Publisher.class, publisherId);
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Publisher>(e.getStatusCode());
		}
	}
	
	//View one publisher
	@GetMapping("/admin/publishers/{publisherId}")
	public ResponseEntity<?> getPubById(@RequestHeader("Accept") String accept, 
			@RequestHeader("Content-Type") String contentType, @PathVariable Integer publisherId) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/publishers/{publisherId}", HttpMethod.GET, 
					new HttpEntity<Publisher>(headers), Publisher.class, publisherId);
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Publisher>(e.getStatusCode());
		}
	}
	
	//View publishers
	@GetMapping("/admin/publishers")
	public ResponseEntity<Iterable<Publisher>> getAllPubs(@RequestHeader("Accept") String accept) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);
		
		try {
		return restTemp.exchange(adminUri + "/publishers", HttpMethod.GET, 
				new HttpEntity<Object>(headers), new ParameterizedTypeReference<Iterable<Publisher>>(){});
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Iterable<Publisher>>(e.getStatusCode());
		}
	}
	
	
	
	/*
	 * Override Orchestrator
	 */
	
	//Override due date
	@PutMapping("/admin/bookloans/duedate")
	public ResponseEntity<?> overDueDate(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@RequestBody BookLoans loans) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/bookloans/duedate", HttpMethod.PUT, 
					new HttpEntity<BookLoans>(loans, headers), BookLoans.class);
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<BookLoans>(e.getStatusCode());
		}
	}
	
	//View book loans
	@GetMapping("/admin/bookloans")
	public ResponseEntity<Iterable<BookLoans>> getBookLoans(@RequestHeader("Accept") String accept) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);
		
		try {
		return restTemp.exchange(adminUri + "/bookloans", HttpMethod.GET, 
				new HttpEntity<Object>(headers), new ParameterizedTypeReference<Iterable<BookLoans>>(){});
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Iterable<BookLoans>>(e.getStatusCode());
		}
	}
            
}
