package com.ss.lms.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.sql.Date;
import java.util.List;

public class UserDTO {

    private int id;

    @NotBlank(message = "First Name is required")
    private String firstName;

    private String lastName;

    @Email(message = "Please enter a valid email")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*()]).{6,}$",
            message = "Password must be 6 characters long and combination of uppercase letters, " +
                    "lowercase letters, numbers, special characters.")
    private String password;

    private float totalFine;
    private Date joinDate;
    private Date dueDate;

    @Valid
    @NotNull(message = "The address is required.")
    private AddressDTO address;

    @Valid
    private List<IssueRecordDTO> issueRecord;

//    @Valid
//    private List<PhoneNumberDTO> phoneNumber;

    public UserDTO(int id, String firstName, String lastName, String email, Date joinDate, Date dueDate,
                   AddressDTO address, List<IssueRecordDTO> issueRecord, String password, float totalFine) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.joinDate = joinDate;
        this.dueDate = dueDate;
        this.address = address;
        this.issueRecord = issueRecord;
        this.password = password;
        this.totalFine = totalFine;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getTotalFine() {
        return totalFine;
    }

    public void setTotalFine(float totalFine) {
        this.totalFine = totalFine;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public List<IssueRecordDTO> getIssueRecord() {
        return issueRecord;
    }

    public void setIssueRecord(List<IssueRecordDTO> issueRecord) {
        this.issueRecord = issueRecord;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", joinDate=" + joinDate +
                ", dueDate=" + dueDate +
                ", address=" + address +
                ", issueRecord=" + issueRecord +
                '}';
    }
}
