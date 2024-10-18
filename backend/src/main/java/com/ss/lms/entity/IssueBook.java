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

    @Column(name = "fine")
    private float fine;

    @Column(name = "is_returned")
    private boolean isReturned;

    @ManyToOne(
        cascade = {CascadeType.DETACH, CascadeType.MERGE,
                CascadeType.PERSIST, CascadeType.REFRESH}
    )
    @JoinColumn(name = "issue_id")
    private IssueRecord issueRecord;

    @ManyToOne(
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH}
    )
    @JoinColumn(name = "copy_id")
    private BookCopy bookCopy;

    public IssueBook() {
    }

    public IssueBook(int isbnNumber, float fine, boolean isReturned) {
        this.isbnNumber = isbnNumber;
        this.fine = fine;
        this.isReturned = isReturned;
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

    public float getFine() {
        return fine;
    }

    public void setFine(float fine) {
        this.fine = fine;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    public IssueRecord getIssueRecord() {
        return issueRecord;
    }

    public void setIssueRecord(IssueRecord issueRecord) {
        this.issueRecord = issueRecord;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    @Override
    public String toString() {
        return "IssueBook{" +
                "id=" + id +
                ", isbn_number=" + isbnNumber +
                ", fine=" + fine +
                ", isReturned=" + isReturned +
                ", issueRecord=" + issueRecord +
                ", bookCopy=" + bookCopy +
                '}';
    }
}
