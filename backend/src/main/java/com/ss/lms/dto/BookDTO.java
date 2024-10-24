package com.ss.lms.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class BookDTO {

    private int id;

    @NotBlank(message = "ISBN Number is required")
    private String isbnNumber;

    @NotBlank(message = "Title of the book is required")
    private String title;

    @Size(min = 4, max = 4, message = "Enter a valid full year")
    private int publishYear;

    private int pages;
    private int edition;

    @Valid
    private PublishingHouseDTO publishingHouse;

    @NotNull(message = "Genre is required")
    @Valid
    private List<GenreDTO> genre;

    @Valid
    @NotNull(message = "Author(s) is required")
    private List<AuthorDTO> author;

    public BookDTO(int id, String isbnNumber, String title, int publishYear, int pages, int edition, PublishingHouseDTO publishingHouse, List<GenreDTO> genre, List<AuthorDTO> author) {
        this.id = id;
        this.isbnNumber = isbnNumber;
        this.title = title;
        this.publishYear = publishYear;
        this.pages = pages;
        this.edition = edition;
        this.publishingHouse = publishingHouse;
        this.genre = genre;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public PublishingHouseDTO getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(PublishingHouseDTO publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public List<GenreDTO> getGenre() {
        return genre;
    }

    public void setGenre(List<GenreDTO> genre) {
        this.genre = genre;
    }

    public List<AuthorDTO> getAuthor() {
        return author;
    }

    public void setAuthor(List<AuthorDTO> author) {
        this.author = author;
    }
}
