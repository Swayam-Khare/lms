package com.ss.lms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "issue_book")
public class IssueBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "isbn_number")
    private int isbnNumber;

    @ManyToOne(
        cascade = {CascadeType.DETACH, CascadeType.MERGE,
                CascadeType.PERSIST, CascadeType.REFRESH}
    )
    @JoinColumn(name = "issue_id")
    private IssueRecord issueRecord;

    public IssueBook() {
    }

    public IssueBook(int isbnNumber) {
        this.isbnNumber = isbnNumber;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(int isbn_number) {
        this.isbnNumber = isbn_number;
    }

    public IssueRecord getIssueRecord() {
        return issueRecord;
    }

    public void setIssueRecord(IssueRecord issueRecord) {
        this.issueRecord = issueRecord;
    }

    @Override
    public String toString() {
        return "IssueBook{" +
                "id=" + id +
                ", isbn_number=" + isbnNumber +
                ", issueRecord=" + issueRecord +
                '}';
    }
}
