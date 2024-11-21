package com.ss.lms.dto;

import jakarta.validation.constraints.NotNull;

public class IssueBookDTO {

    private int id;

    @NotNull(message = "Isbn number is required")
    private int isbnNumber;

    private IssueRecordDTO issueRecord;

    public IssueBookDTO(int id, int isbnNumber, IssueRecordDTO issueRecord) {
        this.id = id;
        this.isbnNumber = isbnNumber;
        this.issueRecord = issueRecord;
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

    public IssueRecordDTO getIssueRecord() {
        return issueRecord;
    }

    public void setIssueRecord(IssueRecordDTO issueRecord) {
        this.issueRecord = issueRecord;
    }

}
