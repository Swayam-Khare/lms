package com.ss.lms.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

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

    @Column(name = "is_returned")
    private boolean isReturned;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "librarian_id")
    private Librarian librarian;

    @OneToMany(
            mappedBy = "issueRecord",
            cascade = CascadeType.ALL
    )
    private List<IssueBook> issueBook;

    public IssueRecord() {
    }

    public IssueRecord(Date issueDate, Date dueDate, boolean isReturned) {
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.isReturned = isReturned;
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

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
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

    public List<IssueBook> getIssueBook() {
        return issueBook;
    }

    public void setIssueBook(List<IssueBook> issueBook) {
        this.issueBook = issueBook;
    }

    @Override
    public String toString() {
        return "IssueRecord{" +
                "id=" + id +
                ", issue_date=" + issueDate +
                ", due_date=" + dueDate +
                ", user=" + user +
                ", isReturned=" + isReturned +
                ", librarian=" + librarian +
                '}';
    }
}
