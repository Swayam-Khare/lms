package com.ss.lms.services.Impl;

import com.ss.lms.dto.BookDTO;
import com.ss.lms.entity.Book;
import com.ss.lms.exception.CustomEntityNotFoundException;
import com.ss.lms.mapper.BookMapper;
import com.ss.lms.repository.BookRepository;
import com.ss.lms.services.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;


@Service
public class BookServiceImpl implements BookService {

    private static final Logger log = Logger.getLogger("Book Service Impl");

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookDTO> getAll() {

        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    @Override
    public BookDTO getById(int id) {

        return bookMapper.toDTO(Objects.requireNonNull(
                bookRepository
                        .findById(id)
                        .orElse(null),
                "No book found with id: " + id
        ));
    }

    @Override
    @Transactional
    public BookDTO create(BookDTO bookDTO) {
        Book book = bookRepository.save(
                bookMapper.toEntity(bookDTO)
        );
        return bookMapper.toDTO(book);
    }

    @Override
    @Transactional
    public BookDTO update(BookDTO bookDTO) {

        Book book = bookRepository
                .findById(bookDTO.getId())
                .orElse(null);

        if (book == null) {
            throw new CustomEntityNotFoundException(
                    "Book not found with id: " + bookDTO.getId());
        }

        book = bookMapper.toEntity(bookDTO);

        return bookMapper.toDTO(
                bookRepository.save(book)
        );
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Book book = bookRepository
                .findById(id)
                .orElse(null);

        if (book == null) {
            throw new CustomEntityNotFoundException(
                    "Book not found with id: " + id);
        }

        bookRepository.deleteById(id);
    }
}
