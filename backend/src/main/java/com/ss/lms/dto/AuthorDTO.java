package com.ss.lms.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

public class AuthorDTO {

    private int id;

    @NotBlank(message = "First Name is required")
    private String firstName;

    private String lastName;

    @Email(message = "Please enter a valid email")
    @NotBlank(message = "Email is required")
    private String email;

    @Valid
    private AddressDTO address;

    @Valid
    private List<BookDTO> book;

    public AuthorDTO(int id, String firstName, String lastName, String email, AddressDTO address,
                     List<BookDTO> book) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public List<BookDTO> getBook() {
        return book;
    }

    public void setBook(List<BookDTO> book) {
        this.book = book;
    }
}
