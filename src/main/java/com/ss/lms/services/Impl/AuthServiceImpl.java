package com.ss.lms.services.Impl;

import com.ss.lms.dto.UserDTO;
import com.ss.lms.mapper.UserMapper;
import com.ss.lms.repository.UserRepository;
import com.ss.lms.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepo;
    private final UserMapper userMapper;

    @Autowired
    public AuthServiceImpl(UserRepository userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public String loginUser(String username, String password) {
        return "";
    }
}
