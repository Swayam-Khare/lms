package com.ss.lms.services;

import com.ss.lms.dto.GenreDTO;

import java.util.List;

public interface GenreService {
    List<GenreDTO> getAll();

    GenreDTO getById(int id);

    GenreDTO create(GenreDTO genreDTO);

    GenreDTO update(GenreDTO genreDTO);

    void deleteById(int id);
}
