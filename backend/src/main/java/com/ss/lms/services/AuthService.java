package com.ss.lms.services;

import com.ss.lms.dto.LibrarianDTO;
import com.ss.lms.dto.UserDTO;
import jakarta.validation.Valid;

public interface AuthService {

    UserDTO registerUser(UserDTO userDTO);

    String loginUser(String username, String password, String role);

    LibrarianDTO registerLibrarian(LibrarianDTO librarianDTO);
}
