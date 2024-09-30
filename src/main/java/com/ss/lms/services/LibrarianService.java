package com.ss.lms.services;

import com.ss.lms.dto.LibrarianDTO;

import java.util.List;

public interface LibrarianService {
    List<LibrarianDTO> getAll();

    LibrarianDTO getById(int id);

    LibrarianDTO create(LibrarianDTO librarianDTO);

    LibrarianDTO update(LibrarianDTO librarianDTO);

    void deleteById(int id);
}
