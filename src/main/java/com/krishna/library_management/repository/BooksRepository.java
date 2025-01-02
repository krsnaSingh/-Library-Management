package com.krishna.library_management.repository;

import com.krishna.library_management.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books, Long> {
}
