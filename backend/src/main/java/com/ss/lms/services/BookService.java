package com.ss.lms.services;

import com.ss.lms.dto.BookDTO;

import java.util.List;

public interface BookService {

    List<BookDTO> getAll();

    BookDTO getById(int id);

    BookDTO create(BookDTO bookDTO);

    BookDTO update(BookDTO bookDTO);

    List<BookDTO> search(String searchText);

    void deleteById(int id);}
