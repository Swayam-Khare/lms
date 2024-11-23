package com.ss.lms.repository;

import com.ss.lms.entity.IssueRecord;
import com.ss.lms.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRecordRepository extends JpaRepository<IssueRecord, Integer> {

    Page<IssueRecord> findByUserOrderByDueDateDesc(User user, Pageable pageable);

    List<IssueRecord> findIssueRecordByLibrarian_Id(int librarianId);

    List<IssueRecord> findIssueRecordByUser_Id(int userId);
}
