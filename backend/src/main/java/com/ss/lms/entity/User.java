package com.ss.lms.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "join_date")
    private Date joinDate;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "total_fine")
    private float totalFine;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private List<IssueRecord> issueRecord;

    public User() {
    }

    public User(String firstName, String lastName, String email, Date joinDate, Date dueDate, String password, float totalFine) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.joinDate = joinDate;
        this.dueDate = dueDate;
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

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date join_date) {
        this.joinDate = join_date;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date due_date) {
        this.dueDate = due_date;
    }

    public float getTotalFine() {
        return totalFine;
    }

    public void setTotalFine(float totalFine) {
        this.totalFine = totalFine;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<IssueRecord> getIssueRecord() {
        return issueRecord;
    }

    public void setIssueRecord(List<IssueRecord> issueRecord) {
        this.issueRecord = issueRecord;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", join_date=" + joinDate +
                ", due_date=" + dueDate +
                ", total_fine=" + totalFine +
                ", address=" + address +
                '}';
    }
}
