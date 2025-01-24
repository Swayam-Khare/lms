package com.ss.lms.repository;

import com.ss.lms.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(
            "SELECT b from Book b JOIN b.author a WHERE " +
                    "LOWER(b.title) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
                    "LOWER(b.isbnNumber) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
                    "CAST(b.publishYear as string) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
                    "LOWER(a.firstName) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
                    "LOWER(a.lastName) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
                    "LOWER(CONCAT(a.firstName, ' ', a.lastName)) LIKE LOWER(CONCAT('%', :searchText, '%'))"
    )
    List<Book> findBySearch(@Param("searchText") String searchText);
}
