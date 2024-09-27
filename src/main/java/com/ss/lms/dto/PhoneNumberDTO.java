package com.ss.lms.dto;

public class PhoneNumberDTO {
    private int id;
    private String number;

    public PhoneNumberDTO(String number, int id) {
        this.number = number;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
