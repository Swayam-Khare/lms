package com.ss.lms.mapper;

import com.ss.lms.dto.GenreDTO;
import com.ss.lms.entity.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    private final BookMapper bookMapper;

    public GenreMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public GenreDTO toDTO(Genre genre) {
        GenreDTO genreDTO = new GenreDTO(
                genre.getId(),
                genre.getGenre(),
                null
        );

        genreDTO.setBook(
                genre.getBook() != null ?
                        genre.getBook().stream().map(bookMapper:: toDTO).toList() :
                        null
        );

        return genreDTO;
    }

    public Genre toEntity(GenreDTO genreDTO) {
        Genre genre = new Genre(
                genreDTO.getGenre()
        );

        genre.setId(genreDTO.getId());

        genre.setBook(
                genreDTO.getBook() != null ?
                        genreDTO.getBook().stream().map(bookMapper::toEntity).toList() :
                        null
        );

        return genre;
    }
}
