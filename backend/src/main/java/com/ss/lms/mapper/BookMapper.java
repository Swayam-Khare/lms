package com.ss.lms.mapper;

import com.ss.lms.dto.BookDTO;
import com.ss.lms.entity.Book;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    private final PublishingHouseMapper publishingHouseMapper;
    private final GenreMapper genreMapper;
    private final AuthorMapper authorMapper;

    public BookMapper(@Lazy PublishingHouseMapper publishingHouseMapper, @Lazy GenreMapper genreMapper, @Lazy AuthorMapper authorMapper) {
        this.publishingHouseMapper = publishingHouseMapper;
        this.genreMapper = genreMapper;
        this.authorMapper = authorMapper;
    }

    public BookDTO toDTO(Book book) {
        BookDTO bookDTO = new BookDTO(
                book.getId(),
                book.getIsbnNumber(),
                book.getTitle(),
                book.getPublishYear(),
                book.getPages(),
                book.getEdition(),
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
                bookDTO.getEdition()
        );

        book.setId(bookDTO.getId());
        book.setPublishingHouse(publishingHouseMapper.toEntity(bookDTO.getPublishingHouse()));

        book.setGenre(
                bookDTO.getGenre() != null ?
                        bookDTO.getGenre().stream().map(genreMapper::toEntity).toList() :
                        null
        );

        book.setAuthor(
                bookDTO.getAuthor() != null ?
                        bookDTO.getAuthor().stream().map(authorMapper::toEntity).toList() :
                        null
        );

        return book;
    }
}
