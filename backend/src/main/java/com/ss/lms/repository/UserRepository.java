package com.ss.lms.repository;

import com.ss.lms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(
            "SELECT u FROM User u WHERE " +
                    "LOWER(u.firstName) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
                    "LOWER(u.lastName) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
                    "LOWER(u.email) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
                    "LOWER(CONCAT(u.firstName, ' ', u.lastName)) LIKE LOWER(CONCAT('%', :searchText, '%'))"
    )
    List<User> findUserBySearch(@Param("searchText") String searchText);

    User findUserByEmail(String email);
}
