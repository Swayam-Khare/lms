package com.ss.lms.repository;

import com.ss.lms.entity.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrarianRepository extends JpaRepository<Librarian, Integer> {
    Librarian findLibrarianByEmail(String email);
}
