package com.ss.lms.repository;

import com.ss.lms.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

    List<Genre> findAllByIdIn(List<Integer> id);
}
