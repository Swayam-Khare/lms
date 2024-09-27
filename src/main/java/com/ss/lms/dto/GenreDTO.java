package com.ss.lms.dto;

import java.util.List;

public class GenreDTO {

    private int id;
    private String genre;
    private List<BookDTO> book;

    public GenreDTO(int id, String genre, List<BookDTO> book) {
        this.id = id;
        this.genre = genre;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<BookDTO> getBook() {
        return book;
    }

    public void setBook(List<BookDTO> book) {
        this.book = book;
    }
}
