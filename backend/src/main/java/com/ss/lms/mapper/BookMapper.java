package com.ss.lms.mapper;

import com.ss.lms.dto.AuthorDTO;
import com.ss.lms.dto.BookDTO;
import com.ss.lms.dto.GenreDTO;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.Genre;
import com.ss.lms.entity.PublishingHouse;
import com.ss.lms.repository.AuthorRepository;
import com.ss.lms.repository.GenreRepository;
import com.ss.lms.repository.PublishingHouseRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookMapper {

    private final GenreMapper genreMapper;
    private final AuthorMapper authorMapper;
    private PublishingHouseMapper publishingHouseMapper;
    private final PublishingHouseRepository publishingHouseRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final ApplicationContext context;

    public BookMapper(@Lazy GenreMapper genreMapper, @Lazy AuthorMapper authorMapper, PublishingHouseRepository publishingHouseRepository, GenreRepository genreRepository, AuthorRepository authorRepository, ApplicationContext context) {
        this.genreMapper = genreMapper;
        this.authorMapper = authorMapper;
        this.publishingHouseRepository = publishingHouseRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
        this.context = context;
    }

    public BookDTO toDTO(Book book) {

        publishingHouseMapper = context.getBean(PublishingHouseMapper.class);

        BookDTO bookDTO = new BookDTO(
                book.getId(),
                book.getIsbnNumber(),
                book.getTitle(),
                book.getPublishYear(),
                book.getPages(),
                book.getEdition(),
                book.getQuantity(),
                book.getAvailable(),
                publishingHouseMapper.toDTO(book.getPublishingHouse()),
                null,
                null
        );

        bookDTO.setAuthor(
                book.getAuthor() != null ?
                        book.getAuthor().stream().map(authorMapper::toDTO).toList() :
                        null
        );

        bookDTO.setGenre(
                book.getGenre() != null ?
                        book.getGenre().stream().map(genreMapper::toDTO).toList() :
                        null
        );

        return bookDTO;
    }

    public Book toEntity(BookDTO bookDTO) {
        Book book = new Book(
                bookDTO.getIsbnNumber(),
                bookDTO.getTitle(),
                bookDTO.getPublishYear(),
                bookDTO.getPages(),
                bookDTO.getEdition(),
                bookDTO.getQuantity(),
                bookDTO.getAvailable()
        );

        book.setId(bookDTO.getId());

        PublishingHouse publishingHouse = publishingHouseRepository
                .findById(bookDTO.getPublishingHouse().getId())
                .orElse(null);

        book.setPublishingHouse(publishingHouse);

        List<Integer> genreIds =  bookDTO
                .getGenre()
                .stream()
                .map(GenreDTO::getId)
                .toList();
        book.setGenre(genreRepository.findAllByIdIn(genreIds));

        List<Integer> authorIds = bookDTO
                .getAuthor()
                .stream()
                .map(AuthorDTO::getId)
                .toList();
        book.setAuthor(authorRepository.findAllByIdIn(authorIds));

        return book;
    }
}
