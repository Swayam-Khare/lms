package com.ss.lms.dto;

public class AddressDTO {

    private int id;
    private String lane1;
    private String lane2;
    private String city;
    private String state;
    private String country;
    private int pincode;

    public AddressDTO(int id, String lane1, String lane2, String city, String state, String country, int pincode) {
        this.id = id;
        this.lane1 = lane1;
        this.lane2 = lane2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
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
}
