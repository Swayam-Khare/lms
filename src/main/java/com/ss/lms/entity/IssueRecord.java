package com.ss.lms.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "issue_record")
public class IssueRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "issue_date")
    private Date issueDate;

    @Column(name = "due_date")
    private Date dueDate;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "librarian_id")
    private Librarian librarian;

    public IssueRecord() {
    }

    public IssueRecord(Date issueDate, Date dueDate) {
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issue_date) {
        this.issueDate = issue_date;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date due_date) {
        this.dueDate = due_date;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    @Override
    public String toString() {
        return "IssueRecord{" +
                "id=" + id +
                ", issue_date=" + issueDate +
                ", due_date=" + dueDate +
                ", book=" + book +
                ", user=" + user +
                ", librarian=" + librarian +
                '}';
    }
}
