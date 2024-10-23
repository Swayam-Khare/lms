package com.ss.lms.rest;

import com.ss.lms.dto.AuthorDTO;
import com.ss.lms.services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthorRestController {

    private final AuthorService authorService;

    @Autowired
    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/")
    public List<AuthorDTO> getAll() {
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    public AuthorDTO getAuthorById(@PathVariable int id) {
        return authorService.getById(id);
    }

    @PostMapping("/")
    public AuthorDTO create(@Valid @RequestBody AuthorDTO authorDTO) {
        return authorService.create(authorDTO);
    }

    @PutMapping("/")
    public AuthorDTO update(@RequestBody AuthorDTO authorDTO) {
        return authorService.update(authorDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        authorService.deleteById(id);
    }
}