package com.ss.lms.mapper;

import com.ss.lms.dto.AddressDTO;
import com.ss.lms.dto.AuthorDTO;
import com.ss.lms.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    private final AddressMapper addressMapper;
    private final BookMapper bookMapper;

    public AuthorMapper(AddressMapper addressMapper, BookMapper bookMapper) {
        this.addressMapper = addressMapper;
        this.bookMapper = bookMapper;
    }

    public AuthorDTO toDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO(
                author.getId(),
                author.getFirstName(),
                author.getLastName(),
                author.getEmail(),
                null,
                null
        );

        authorDTO.setAddress(
                author.getAddress() != null ?
                        addressMapper.toDTO(author.getAddress()) :
                        null
        );

        authorDTO.setBook(
                author.getBook() != null ?
                        author.getBook().stream().map(bookMapper::toDTO).toList() :
                        null
        );

        return authorDTO;
    }

    public Author toEntity(AuthorDTO authorDTO) {
        Author author = new Author(
                authorDTO.getFirstName(),
                authorDTO.getLastName(),
                authorDTO.getEmail()
        );

        author.setId(authorDTO.getId());
        author.setAddress(
                authorDTO.getAddress() != null ?
                        addressMapper.toEntity(authorDTO.getAddress()) :
                        null
        );

        author.setBook(
                authorDTO.getBook() != null ?
                        authorDTO.getBook().stream().map(bookMapper::toEntity).toList() :
                        null
        );

        return author;
    }
}
