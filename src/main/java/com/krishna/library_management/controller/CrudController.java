package com.krishna.library_management.controller;

import com.krishna.library_management.entities.Books;
import com.krishna.library_management.services.CrudServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("library")
public class CrudController {

    @Autowired
    CrudServices crudServices;

    @GetMapping("/test")
    public String test(){
        return "feeling fine ";
    }

    @PostMapping("/add")
    public ResponseEntity<?> insertNewBook(@RequestBody Books newBooks) {
        Books book;
        try {
           book =  crudServices.addNewBook(newBooks);
           if(book != null) {
               return  ResponseEntity.status(HttpStatus.CREATED).body(book);
           } else {
               return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is some Server issue from our side. Please try after some time.");
           }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<?> getAllBooks(){
        List<Books> booklist;
        try {
             booklist =  crudServices.getAllBooks();
             if(!booklist.isEmpty()) {
                 return ResponseEntity.ok(booklist);
             } else {
                 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No book data Found :)");
             }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getBookByISBN/{id}")
    public ResponseEntity<?> getBookByISBN(@PathVariable Long id) {
        try {
            Optional<Books> book = crudServices.getBookById(id);
            if(book.isPresent()) {
                return ResponseEntity.ok(book.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Book with ISBN " + id + " not found :)");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/issueBook/{id}")
    public ResponseEntity<?> issueBook(@PathVariable Long id) {
        try{
            Books issuedBook = crudServices.issueBook(id);
            return ResponseEntity.ok("Book "+issuedBook.getBookName()+ " Issued SuccessFully! Keep learning :)");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/returnBook/{id}")
    public ResponseEntity<?> returnBook(@PathVariable Long id) {
        try{
            Books issuedBook = crudServices.returnBook(id);
            return ResponseEntity.ok("Book returned SuccessFully! Hope you enjoyed reading "+ issuedBook.getBookName() +":)");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }

}
