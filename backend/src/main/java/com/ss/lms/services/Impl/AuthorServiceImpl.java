package com.ss.lms.services.Impl;

import com.ss.lms.dto.AuthorDTO;
import com.ss.lms.entity.Author;
import com.ss.lms.mapper.AuthorMapper;
import com.ss.lms.repository.AuthorRepository;
import com.ss.lms.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;


@Service
public class AuthorServiceImpl implements AuthorService {

    private static final Logger log = Logger.getLogger("Author Service Impl");

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
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
            // TODO: throw custom exception "author not found"
            return null;
        }

        author = authorMapper.toEntity(authorDTO);

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
            // TODO: throw custom exception "author not found"
            return;
        }

        authorRepository.deleteById(id);
    }
}

