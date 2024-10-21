package com.ss.lms.repository;

import com.ss.lms.entity.IssueRecord;
import com.ss.lms.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRecordRepository extends JpaRepository<IssueRecord, Integer> {

    Page<IssueRecord> findByUserOrderByDueDateDesc(User user, Pageable pageable);
}
