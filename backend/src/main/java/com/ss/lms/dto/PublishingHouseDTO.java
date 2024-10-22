package com.ss.lms.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class PublishingHouseDTO {

    private int id;

    @NotBlank(message = "Name field is required")
    private String name;

    @NotNull
    @Email(message = "Please enter a valid Email")
    private String email;

    @Valid
    @NotNull(message = "Address is required")
    private AddressDTO address;

    @Valid
    private List<BookDTO> book;

    @Valid
    private List<PhoneNumberDTO> phoneNumber;

    public PublishingHouseDTO(int id, String name, String email, AddressDTO address, List<BookDTO> book, List<PhoneNumberDTO> phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.book = book;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public List<BookDTO> getBook() {
        return book;
    }

    public List<PhoneNumberDTO> getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public void setBook(List<BookDTO> book) {
        this.book = book;
    }

    public void setPhoneNumber(List<PhoneNumberDTO> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
