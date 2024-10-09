package com.ss.lms.services;

import com.ss.lms.dto.AuthorDTO;

import java.util.List;

public interface AuthorService {
    List<AuthorDTO> getAll();

    AuthorDTO getById(int id);

    AuthorDTO create(AuthorDTO authorDTO);

    AuthorDTO update(AuthorDTO authorDTO);

    void deleteById(int id);
}
