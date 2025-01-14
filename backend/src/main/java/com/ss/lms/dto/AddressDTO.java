package com.ss.lms.dto;

import jakarta.validation.constraints.*;

public class AddressDTO {

    private int id;

    @NotNull(message = "Lane 1 is required")
    private String lane1;

    private String lane2;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Country is required")
    private String country;

    @NotNull(message = "Pincode is required")
    @Min(value = 100000, message = "Pincode must contain at least 6 digits")
    @Max(value = 999999, message = "Pincode must contain at most 6 digits")
    private int pincode;

    @Size(min = 10, max = 13, message = "Phone number must contain 10 digits")
    private String phoneNumber;

    public AddressDTO(int id, String lane1, String lane2, String city, String state,
                      String country, int pincode, String phoneNumber) {
        this.id = id;
        this.lane1 = lane1;
        this.lane2 = lane2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLane1() {
        return lane1;
    }

    public void setLane1(String lane1) {
        this.lane1 = lane1;
    }

    public String getLane2() {
        return lane2;
    }

    public void setLane2(String lane2) {
        this.lane2 = lane2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public @Size(min = 10, max = 12, message = "Phone number must contain 10 digits")
            String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@Size(min = 10, max = 13, message = "Phone number must contain 10 digits")
                               String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
