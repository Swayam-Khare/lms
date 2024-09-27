package com.ss.lms.dto;

import com.ss.lms.entity.PhoneNumber;

import java.util.List;

public class AuthorDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private AddressDTO address;
    private List<BookDTO> book;
    private List<PhoneNumberDTO> phoneNumber;

    public AuthorDTO(int id, String firstName, String lastName, String email, AddressDTO address, List<BookDTO> book, List<PhoneNumberDTO> phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.book = book;
        this.phoneNumber = phoneNumber;
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

    public List<PhoneNumberDTO> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(List<PhoneNumberDTO> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
