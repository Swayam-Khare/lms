package com.ss.lms.services;

import com.ss.lms.dto.LibrarianDTO;
import com.ss.lms.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    UserDTO registerUser(UserDTO userDTO);

    String login(String username, String password, String role);

    LibrarianDTO registerLibrarian(LibrarianDTO librarianDTO);

    ResponseEntity<?> getDetails();
}
