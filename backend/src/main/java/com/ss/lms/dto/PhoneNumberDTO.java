package com.ss.lms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PhoneNumberDTO {
    private int id;

    @NotNull
    @Size(min = 10, max = 10, message = "Phone number must contain 10 digits")
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
