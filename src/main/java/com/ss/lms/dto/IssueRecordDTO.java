package com.ss.lms.dto;

import java.sql.Date;

public class IssueRecordDTO {

    private int id;
    private Date issueDate;
    private Date dueDate;
    private BookDTO book;
    private UserDTO user;
    private LibrarianDTO librarian;

    public IssueRecordDTO(int id, Date issueDate, Date dueDate, BookDTO book, UserDTO user, LibrarianDTO librarian) {
        this.id = id;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.book = book;
        this.user = user;
        this.librarian = librarian;
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

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public LibrarianDTO getLibrarian() {
        return librarian;
    }

    public void setLibrarian(LibrarianDTO librarian) {
        this.librarian = librarian;
    }
}
