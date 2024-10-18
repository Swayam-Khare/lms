package com.ss.lms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book_copy")
public class BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "copy_id")
    private int copyId;

    @Column(name = "is_available")
    private boolean isAvailable;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "book_id")
    private Book book;

    public BookCopy() {
    }

    public BookCopy(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public int getCopyId() {
        return copyId;
    }

    public void setCopyId(int copy_id) {
        this.copyId = copy_id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "BookCopy{" +
                "copy_id=" + copyId +
                ", isAvailable=" + isAvailable +
                ", book=" + book +
                '}';
    }
}
