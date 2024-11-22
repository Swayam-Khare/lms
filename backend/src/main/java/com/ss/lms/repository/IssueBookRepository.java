package com.ss.lms.repository;

import com.ss.lms.entity.IssueBook;
import com.ss.lms.entity.IssueRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueBookRepository extends JpaRepository<IssueBook, Integer> {
    List<IssueBook> findIssueBookByIssueRecord(IssueRecord issueRecord);
}
