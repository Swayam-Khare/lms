package com.ss.lms.dto;

import java.sql.Date;
import java.util.List;

public class UserDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date joinDate;
    private Date dueDate;
    private AddressDTO address;
    private List<IssueRecordDTO> issueRecord;
    private List<PhoneNumberDTO> phoneNumber;

    public UserDTO(int id, String firstName, String lastName, String email, Date joinDate, Date dueDate, AddressDTO address, List<IssueRecordDTO> issueRecord, List<PhoneNumberDTO> phoneNumber, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.joinDate = joinDate;
        this.dueDate = dueDate;
        this.address = address;
        this.issueRecord = issueRecord;
        this.phoneNumber = phoneNumber;
        this.password = password;
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

    public List<PhoneNumberDTO> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(List<PhoneNumberDTO> phoneNumber) {
        this.phoneNumber = phoneNumber;
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
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
