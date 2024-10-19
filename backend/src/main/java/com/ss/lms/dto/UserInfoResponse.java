package com.ss.lms.dto;

import java.sql.Date;
import java.util.List;

public class UserInfoResponse {

    private float totalFine;

    private int bookToReturn;

    private int totalBooksBorrowed;

    private Date membershipEndDate;

    private List<IssueRecordDTO> latestIssues;

    public UserInfoResponse() {
    }

    public UserInfoResponse(float totalFine, int bookToReturn, int totalBooksBorrowed, Date membershipEndDate, List<IssueRecordDTO> latestIssues) {
        this.totalFine = totalFine;
        this.bookToReturn = bookToReturn;
        this.totalBooksBorrowed = totalBooksBorrowed;
        this.membershipEndDate = membershipEndDate;
        this.latestIssues = latestIssues;
    }

    public float getTotalFine() {
        return totalFine;
    }

    public void setTotalFine(float totalFine) {
        this.totalFine = totalFine;
    }

    public int getBookToReturn() {
        return bookToReturn;
    }

    public void setBookToReturn(int bookToReturn) {
        this.bookToReturn = bookToReturn;
    }

    public int getTotalBooksBorrowed() {
        return totalBooksBorrowed;
    }

    public void setTotalBooksBorrowed(int totalBooksBorrowed) {
        this.totalBooksBorrowed = totalBooksBorrowed;
    }

    public Date getMembershipEndDate() {
        return membershipEndDate;
    }

    public void setMembershipEndDate(Date membershipEndDate) {
        this.membershipEndDate = membershipEndDate;
    }

    public List<IssueRecordDTO> getLatestIssues() {
        return latestIssues;
    }

    public void setLatestIssues(List<IssueRecordDTO> latestIssues) {
        this.latestIssues = latestIssues;
    }
}
