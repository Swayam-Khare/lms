package com.ss.lms.rest;

import com.ss.lms.dto.GenreDTO;
import com.ss.lms.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/genre")
public class GenreRestController {

    private final GenreService genreService;

    @Autowired
    public GenreRestController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/")
    public List<GenreDTO> getAll() {
        return genreService.getAll();
    }

    @GetMapping("/{id}")
    public GenreDTO getGenreById(@PathVariable int id) {
        return genreService.getById(id);
    }

    @PostMapping("/")
    public GenreDTO create(@RequestBody GenreDTO genreDTO) {
        return genreService.create(genreDTO);
    }

    @PutMapping("/")
    public GenreDTO update(@RequestBody GenreDTO genreDTO) {
        return genreService.update(genreDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        genreService.deleteById(id);
    }
}