package com.ss.lms.services.Impl;

import com.ss.lms.dto.LibrarianDTO;
import com.ss.lms.entity.Librarian;
import com.ss.lms.mapper.LibrarianMapper;
import com.ss.lms.repository.LibrarianRepository;
import com.ss.lms.services.LibrarianService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class LibrarianServiceImpl implements LibrarianService {

    private static final Logger log = Logger.getLogger("Librarian Service Impl");

    private final LibrarianRepository librarianRepository;
    private final LibrarianMapper librarianMapper;

    public LibrarianServiceImpl(LibrarianRepository librarianRepository, LibrarianMapper librarianMapper) {
        this.librarianRepository = librarianRepository;
        this.librarianMapper = librarianMapper;
    }

    @Override
    public List<LibrarianDTO> getAll() {

        return librarianRepository.findAll()
                .stream()
                .map(librarianMapper::toDTO)
                .toList();
    }

    @Override
    public LibrarianDTO getById(int id) {

        return librarianMapper.toDTO(Objects.requireNonNull(
                librarianRepository
                        .findById(id)
                        .orElse(null),
                "No Librarian found with id: " + id
        ));
    }

    @Override
    public LibrarianDTO create(LibrarianDTO librarianDTO) {

        Librarian librarian = librarianRepository.save(
                librarianMapper.toEntity(librarianDTO)
        );
        return librarianMapper.toDTO(librarian);
    }

    @Override
    public LibrarianDTO update(LibrarianDTO librarianDTO) {

        Librarian librarian = librarianRepository
                .findById(librarianDTO.getId())
                .orElse(null);

        if (librarian == null) {
            // TODO: throw custom exception "Librarian not found"
            return null;
        }

        librarian = librarianMapper.toEntity(librarianDTO);

        return librarianMapper.toDTO(
                librarianRepository.save(librarian)
        );
    }

    @Override
    public void deleteById(int id) {

        Librarian librarian = librarianRepository
                .findById(id)
                .orElse(null);

        if (librarian == null) {
            // TODO: throw custom exception "Librarian not found"
            return;
        }

        librarianRepository.deleteById(id);
    }
}
