package com.ss.lms.services.Impl;

import com.ss.lms.dto.AuthorDTO;
import com.ss.lms.entity.Author;
import com.ss.lms.exception.CustomEntityNotFoundException;
import com.ss.lms.mapper.AddressMapper;
import com.ss.lms.mapper.AuthorMapper;
import com.ss.lms.mapper.BookMapper;
import com.ss.lms.repository.AuthorRepository;
import com.ss.lms.services.AuthorService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class AuthorServiceImpl implements AuthorService {

//    private static final Logger log = Logger.getLogger("Author Service Impl");

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final AddressMapper addressMapper;
    private final BookMapper bookMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper,
                             AddressMapper addressMapper, @Lazy BookMapper bookMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
        this.addressMapper = addressMapper;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<AuthorDTO> getAll() {

        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toDTO)
                .toList();
    }

    @Override
    public AuthorDTO getById(int id) {

        return authorMapper.toDTO(Objects.requireNonNull(
                authorRepository
                        .findById(id)
                        .orElse(null),
                "No author found with id: " + id
        ));
    }

    @Override
    public AuthorDTO create(AuthorDTO authorDTO) {

        Author author = authorRepository.save(
                authorMapper.toEntity(authorDTO)
        );
        return authorMapper.toDTO(author);
    }

    @Override
    public AuthorDTO update(AuthorDTO authorDTO) {

        Author author = authorRepository
                .findById(authorDTO.getId())
                .orElse(null);

        if (author == null) {
            throw new CustomEntityNotFoundException("No author found with id: " + authorDTO.getId());
        }

        author.setFirstName(authorDTO.getFirstName() == null ? author.getFirstName() : authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName() == null ? author.getLastName() : authorDTO.getLastName());
        author.setEmail(authorDTO.getEmail() == null ? author.getEmail() : authorDTO.getEmail());

        author.setAddress(
                authorDTO.getAddress() == null ?
                        author.getAddress() :
                        addressMapper.toEntity(authorDTO.getAddress())
        );

        author.setBook(
                authorDTO.getBook() == null ?
                        author.getBook() :
                        authorDTO.getBook().stream().map(bookMapper::toEntity).toList()
        );

        return authorMapper.toDTO(
                authorRepository.save(author)
        );
    }

    @Override
    public void deleteById(int id) {

        Author author = authorRepository
                .findById(id)
                .orElse(null);

        if (author == null) {
            throw new CustomEntityNotFoundException("No author found with id: " + id);
        }

        authorRepository.deleteById(id);
    }
}

