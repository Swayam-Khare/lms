package com.ss.lms.services.Impl;

import com.ss.lms.dto.UserDTO;
import com.ss.lms.mapper.UserMapper;
import com.ss.lms.repository.UserRepository;
import com.ss.lms.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepo;
    private final UserMapper userMapper;
    private AuthenticationManager authManager;
    private final JWTService jwtService;

    @Autowired
    public AuthServiceImpl(UserRepository userRepo, UserMapper userMapper, AuthenticationManager authManager, JWTService jwtService) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public String loginUser(String username, String password) {
        Authentication authentication =  authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        if (authentication.isAuthenticated())
            return jwtService.generateToken(username);

        return "fail";
    }
}
