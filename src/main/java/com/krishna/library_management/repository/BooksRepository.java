package com.krishna.library_management.repository;

import com.krishna.library_management.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BooksRepository extends JpaRepository<Books, Long> {
    Page<Books> findAll(Pageable pageable);
}
