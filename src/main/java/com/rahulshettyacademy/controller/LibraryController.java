package com.rahulshettyacademy.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.rahulshettyacademy.repository.LibraryRepository;
import com.rahulshettyacademy.service.LibraryService;

@Slf4j
@RestController
public class LibraryController {

    @Autowired
    LibraryRepository repository;

    @Autowired
    LibraryService libraryService;

//    private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);

    @PostMapping("/addBook")
    public ResponseEntity addBookImplementation(@RequestBody Library library) {

        String id = libraryService.buildId(library.getIsbn(), library.getAisle());//dependenyMock
        AddResponse ad = new AddResponse();

        if (!libraryService.checkBookAlreadyExist(id))//mock
        {
            log.info("Book do not exist so creating one");
            library.setId(id);
            repository.save(library);//mock
            HttpHeaders headers = new HttpHeaders();
            headers.add("Developer Name", "Omid Ashouri");

            ad.setMsg("Success Book is Added");
            ad.setId(id);
            //return ad;
            return new ResponseEntity<AddResponse>(ad, headers, HttpStatus.CREATED);
        } else {
            log.info("Book  exist so skipping creation");
            ad.setMsg("Book already exist");
            ad.setId(id);
            return new ResponseEntity<AddResponse>(ad, HttpStatus.ACCEPTED);
        }
        //write the code to tell book already exist

    }

    @GetMapping("/getBooks/{id}")
    public ResponseEntity<?> getBookById(@PathVariable(value = "id") String id) {
        try {
            Library library = repository.findById(id).get();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Developer Name", "Omid Ashouri");
            return new ResponseEntity<Library>(library, headers, HttpStatus.FOUND);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getBooks/author")
    public List<Library> getBookByAuthorName(@RequestParam(value = "authorname") String authorname) {
        return repository.findAllByAuthor(authorname);
    }

    @PutMapping("/updateBook/{id}")
    public ResponseEntity<Library> updateBook(@PathVariable(value = "id") String id, @RequestBody Library library) {
        //	Library existingBook = repository.findById(id).get();//mock
        Library existingBook = libraryService.getBookById(id);

        existingBook.setAisle(library.getAisle());//mock
        existingBook.setAuthor(library.getAuthor());
        existingBook.setBook_name(library.getBook_name());
        repository.save(existingBook);//

        return new ResponseEntity<Library>(existingBook, HttpStatus.OK);
    }

    @DeleteMapping("/deleteBook")
    public ResponseEntity<String> deleteBookById(@RequestBody Library library) {
        //	Library libdelete =repository.findById(library.getId()).get();
        Library libdelete = libraryService.getBookById(library.getId());//mock
        repository.delete(libdelete);

        log.info("Book  is deleted ");
        return new ResponseEntity<>("Book is deleted", HttpStatus.CREATED);

    }

    @GetMapping("/getBooks")
    public List<Library> getBooks() {
        return repository.findAll();
    }


}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

