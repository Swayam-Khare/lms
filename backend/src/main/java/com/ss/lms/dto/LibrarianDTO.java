package com.ss.lms.dto;

import com.ss.lms.entity.Address;
import com.ss.lms.entity.PhoneNumber;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

public class LibrarianDTO {
    private int id;

    @NotBlank(message = "First Name is required")
    private String firstName;

    private String lastName;

    @Email(message = "Please enter a valid email")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*()]).{6,}$", message = "Password must be 6 characters long and combination of uppercase letters, lowercase letters, numbers, special characters.")
    private String password;

    @Valid
//    @NotNull(message = "The address is required.")
    private AddressDTO address;

//    @Valid
//    private List<PhoneNumberDTO> phoneNumber;

    public LibrarianDTO(int id, String firstName, String email, String lastName, AddressDTO address, String password) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
        this.address = address;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
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
}
