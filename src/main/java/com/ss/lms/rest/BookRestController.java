package com.ss.lms.rest;

import com.ss.lms.dto.BookDTO;
import com.ss.lms.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/book")
@CrossOrigin(origins = "http://localhost:5173")
public class BookRestController {

    private final BookService bookService;

    @Autowired
    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public List<BookDTO> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable int id) {
        return bookService.getById(id);
    }

    @PostMapping("/")
    public BookDTO create(@RequestBody BookDTO bookDTO) {
        return bookService.create(bookDTO);
    }

    @PutMapping("/")
    public BookDTO update(@RequestBody BookDTO bookDTO) {
        return bookService.update(bookDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        bookService.deleteById(id);
    }
}