package com.ss.lms.repository;

import com.ss.lms.entity.IssueRecord;
import com.ss.lms.entity.Librarian;
import com.ss.lms.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueRecordRepository extends JpaRepository<IssueRecord, Integer> {

    @Query(
            "SELECT i from IssueRecord i JOIN i.user u WHERE " +
                    "i.librarian = :lib AND " +
                    "(LOWER(u.firstName) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
                    "LOWER(u.lastName) LIKE LOWER(CONCAT('%', :searchText, '%')))"
    )
    List<IssueRecord> findByUser(@Param("searchText") String searchText, @Param("lib") Librarian lib);

    @Query(
            "SELECT i from IssueRecord i JOIN i.librarian l WHERE " +
                    "i.user = :user AND " +
                    "(LOWER(l.firstName) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
                    "LOWER(l.lastName) LIKE LOWER(CONCAT('%', :searchText, '%')))"
    )
    List<IssueRecord> findByLibrarian(@Param("searchText") String searchText, @Param("user") User user);

    Page<IssueRecord> findByUserOrderByDueDateDesc(User user, Pageable pageable);

    List<IssueRecord> findIssueRecordByLibrarian_Id(int librarianId);

    List<IssueRecord> findIssueRecordByUser_Id(int userId);
}
