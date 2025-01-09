package com.ss.lms.services.Impl;

import com.ss.lms.dto.GenreDTO;
import com.ss.lms.entity.Genre;
import com.ss.lms.mapper.GenreMapper;
import com.ss.lms.repository.GenreRepository;
import com.ss.lms.services.GenreService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class GenreServiceImpl implements GenreService {

    private static final Logger log = Logger.getLogger("Genre Service Impl");

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreServiceImpl(GenreRepository genreRepository, @Lazy GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }

    @Override
    public List<GenreDTO> getAll() {

        return genreRepository.findAll()
                .stream()
                .map(genreMapper::toDTO)
                .toList();
    }

    @Override
    public GenreDTO getById(int id) {

        return genreMapper.toDTO(Objects.requireNonNull(
                genreRepository
                        .findById(id)
                        .orElse(null),
                "No genre found with id: " + id
        ));
    }

    @Override
    public GenreDTO create(GenreDTO genreDTO) {

        Genre genre = genreRepository.save(
                genreMapper.toEntity(genreDTO)
        );
        return genreMapper.toDTO(genre);
    }

    @Override
    @Transactional
    public GenreDTO update(GenreDTO genreDTO) {

        Genre genre = genreRepository
                .findById(genreDTO.getId())
                .orElse(null);

        if (genre == null) {
            // TODO: throw custom exception "genre not found"
            return null;
        }

        genre = genreMapper.toEntity(genreDTO);

        return genreMapper.toDTO(
                genreRepository.save(genre)
        );
    }

    @Override
    public void deleteById(int id) {

        Genre genre = genreRepository
                .findById(id)
                .orElse(null);

        if (genre == null) {
            // TODO: throw custom exception "genre not found"
            return;
        }

        genreRepository.deleteById(id);
    }
}
