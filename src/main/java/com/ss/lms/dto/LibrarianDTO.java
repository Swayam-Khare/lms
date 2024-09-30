package com.ss.lms.dto;

import com.ss.lms.entity.Address;
import com.ss.lms.entity.PhoneNumber;

import java.util.List;

public class LibrarianDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private AddressDTO address;
    private List<PhoneNumberDTO> phoneNumber;

    public LibrarianDTO(int id, String firstName, String email, String lastName, AddressDTO address, List<PhoneNumberDTO> phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public void setPhoneNumber(List<PhoneNumberDTO> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public List<PhoneNumberDTO> getPhoneNumber() {
        return phoneNumber;
    }
}
