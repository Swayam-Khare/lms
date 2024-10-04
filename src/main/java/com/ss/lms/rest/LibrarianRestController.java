package com.ss.lms.rest;

import com.ss.lms.dto.LibrarianDTO;
import com.ss.lms.services.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/librarian")
@CrossOrigin(origins = "http://localhost:5173")
public class LibrarianRestController {

    private final LibrarianService librarianService;

    @Autowired
    public LibrarianRestController(LibrarianService librarianService) {
        this.librarianService = librarianService;
    }

    @GetMapping("/")
    public List<LibrarianDTO> getAll() {
        return librarianService.getAll();
    }

    @GetMapping("/{id}")
    public LibrarianDTO getLibrarianById(@PathVariable int id) {
        return librarianService.getById(id);
    }

    @PostMapping("/")
    public LibrarianDTO create(@RequestBody LibrarianDTO librarianDTO) {
        return librarianService.create(librarianDTO);
    }

    @PutMapping("/")
    public LibrarianDTO update(@RequestBody LibrarianDTO librarianDTO) {
        return librarianService.update(librarianDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        librarianService.deleteById(id);
    }
}