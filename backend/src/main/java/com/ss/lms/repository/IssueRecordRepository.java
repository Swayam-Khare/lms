package com.ss.lms.repository;

import com.ss.lms.entity.IssueRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRecordRepository extends JpaRepository<IssueRecord, Integer> {
}
