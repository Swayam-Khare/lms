package com.ss.lms.dto;

import jakarta.validation.Valid;

public class BookCopyDTO {

    private int copyId;

    private boolean isAvailable;

    @Valid
    private BookDTO book;

    public BookCopyDTO(int copyId, boolean isAvailable, BookDTO book) {
        this.copyId = copyId;
        this.isAvailable = isAvailable;
        this.book = book;
    }

    public int getCopyId() {
        return copyId;
    }

    public void setCopyId(int copyId) {
        this.copyId = copyId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public @Valid BookDTO getBook() {
        return book;
    }

    public void setBook(@Valid BookDTO book) {
        this.book = book;
    }
}
