package com.ss.lms.dto;

public class IssueBookDTO {

    private int id;

    private int isbnNumber;

    private float fine;

    private boolean isReturned;

    private IssueRecordDTO issueRecord;

    private BookCopyDTO bookCopy;

    public IssueBookDTO(int id, int isbnNumber, float fine, boolean isReturned, IssueRecordDTO issueRecord, BookCopyDTO bookCopy) {
        this.id = id;
        this.isbnNumber = isbnNumber;
        this.fine = fine;
        this.isReturned = isReturned;
        this.issueRecord = issueRecord;
        this.bookCopy = bookCopy;
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

    public void setIsbnNumber(int isbnNumber) {
        this.isbnNumber = isbnNumber;
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

    public IssueRecordDTO getIssueRecord() {
        return issueRecord;
    }

    public void setIssueRecord(IssueRecordDTO issueRecord) {
        this.issueRecord = issueRecord;
    }

    public BookCopyDTO getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopyDTO bookCopy) {
        this.bookCopy = bookCopy;
    }
}
