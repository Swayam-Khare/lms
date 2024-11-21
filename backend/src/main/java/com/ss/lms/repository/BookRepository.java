package com.ss.lms.repository;

import com.ss.lms.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findBookByIsbnNumber(String isbnNumber);
}
