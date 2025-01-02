package com.krishna.library_management.services;

import com.krishna.library_management.entities.Books;
import com.krishna.library_management.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Component
public class CrudServices {

    @Autowired
    BooksRepository booksRepository;

    public Books addNewBook(Books newBook) throws Exception {
        booksRepository.save(newBook);
        return newBook;
    }

    public List<Books> getAllBooks(){
        List<Books> booklist = booksRepository.findAll();
        return booklist;
    }

    public Page<Books> getPaginatedBooks(Pageable pageable) {
        return booksRepository.findAll(pageable);
    }

    public Optional<Books> getBookById(Long id) {
        return booksRepository.findById(id);
    }

    public Books issueBook(Long id) {
        Optional<Books> bookExist = getBookById(id);
        if (bookExist.isPresent()) {
            Books book = bookExist.get();
            Integer prevQty = book.getStockQuantity();
            if (prevQty > 0) {
                book.setStockQuantity(prevQty - 1);
                return booksRepository.save(book);
            } else {
                throw new IllegalStateException("Book is out of stock and cannot be issued.");
            }
        } else {
            throw new NoSuchElementException("Book with ID " + id + " not found.");
        }
    }

    public Books returnBook(Long id) {
        Optional<Books> bookExist = getBookById(id);
        if (bookExist.isPresent()) {
            Books book = bookExist.get();
            Integer prevQty = book.getStockQuantity();
            book.setStockQuantity(prevQty + 1);
            return booksRepository.save(book);
        } else {
            throw new NoSuchElementException("Book with ID " + id + " not found.");
        }
    }
}
