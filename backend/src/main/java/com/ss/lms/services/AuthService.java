package com.ss.lms.services;

import com.ss.lms.dto.UserDTO;

public interface AuthService {

    UserDTO registerUser(UserDTO userDTO);

    String loginUser(String username, String password, String role);
}
