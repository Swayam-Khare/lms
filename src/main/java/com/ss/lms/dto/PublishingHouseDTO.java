package com.ss.lms.dto;

import com.ss.lms.entity.Address;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.PhoneNumber;

import java.util.List;

public class PublishingHouseDTO {

    private int id;
    private String name;
    private String email;
    private Address address;
    private List<BookDTO> book;
    private List<PhoneNumberDTO> phoneNumber;

    public PublishingHouseDTO(int id, String name, String email, Address address, List<BookDTO> book, List<PhoneNumberDTO> phoneNumber) {
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

    public Address getAddress() {
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

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setBook(List<BookDTO> book) {
        this.book = book;
    }

    public void setPhoneNumber(List<PhoneNumberDTO> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
