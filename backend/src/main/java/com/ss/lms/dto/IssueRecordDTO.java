package com.ss.lms.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.util.List;

public class IssueRecordDTO {

    private int id;

    @NotNull(message = "Issue Date is required")
    private Date issueDate;

    @NotNull(message = "Due Date is required")
    private Date dueDate;

    private boolean isReturned;

    @Valid
    @NotNull(message = "details are required")
    private UserDTO user;

    @Valid
    private LibrarianDTO librarian;

    @Valid
    private List<IssueBookDTO> issueBook;

    public IssueRecordDTO(int id, Date issueDate, Date dueDate, boolean isReturned, UserDTO user,
                          LibrarianDTO librarian, List<IssueBookDTO> issueBook) {
        this.id = id;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.isReturned = isReturned;
        this.user = user;
        this.librarian = librarian;
        this.issueBook = issueBook;
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

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
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

    public @Valid List<IssueBookDTO> getIssueBook() {
        return issueBook;
    }

    public void setIssueBook(@Valid List<IssueBookDTO> issueBook) {
        this.issueBook = issueBook;
    }
}
