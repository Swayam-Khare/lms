package com.ss.lms.mapper;

import com.ss.lms.dto.AddressDTO;
import com.ss.lms.dto.AuthorDTO;
import com.ss.lms.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    private final AddressMapper addressMapper;
    private final BookMapper bookMapper;
    private final PhoneNumberMapper phoneNumberMapper;

    public AuthorMapper(AddressMapper addressMapper, BookMapper bookMapper, PhoneNumberMapper phoneNumberMapper) {
        this.addressMapper = addressMapper;
        this.bookMapper = bookMapper;
        this.phoneNumberMapper = phoneNumberMapper;
    }

    public AuthorDTO toDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO(
                author.getId(),
                author.getFirstName(),
                author.getLastName(),
                author.getEmail(),
                addressMapper.toDTO(author.getAddress()),
                null,
                null
        );

        authorDTO.setBook(
                author.getBook() != null ?
                        author.getBook().stream().map(bookMapper::toDTO).toList() :
                        null
        );

        authorDTO.setPhoneNumber(
                author.getPhoneNumber() != null ?
                        author.getPhoneNumber().stream().map(phoneNumberMapper::toDTO).toList() :
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
        author.setAddress(addressMapper.toEntity(authorDTO.getAddress()));

        author.setBook(
                authorDTO.getBook() != null ?
                        authorDTO.getBook().stream().map(bookMapper::toEntity).toList() :
                        null
        );

        author.setPhoneNumber(
                authorDTO.getPhoneNumber() != null ?
                        authorDTO.getPhoneNumber().stream().map(phoneNumberMapper::toEntity).toList() :
                        null
        );

        return author;
    }
}
