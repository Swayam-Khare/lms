package com.ss.lms.repository;

import com.ss.lms.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    List<Author> findAllByIdIn(List<Integer> id);
}
