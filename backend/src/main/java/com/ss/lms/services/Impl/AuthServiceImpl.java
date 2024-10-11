package com.ss.lms.services.Impl;

import com.ss.lms.dto.UserDTO;
import com.ss.lms.entity.User;
import com.ss.lms.mapper.UserMapper;
import com.ss.lms.repository.UserRepository;
import com.ss.lms.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepo;
    private final UserMapper userMapper;
    private final AuthenticationManager authManager;
    private final JWTService jwtService;
    private final ApplicationContext context;
    private final MyLibrarianDetailsService librarianDetailsService;
    private final MyUserDetailsService userDetailsService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    public AuthServiceImpl(UserRepository userRepo, UserMapper userMapper, AuthenticationManager authManager, JWTService jwtService, ApplicationContext context, MyLibrarianDetailsService librarianDetailsService, MyUserDetailsService userDetailsService) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.context = context;
        this.librarianDetailsService = librarianDetailsService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(encoder.encode(user.getPassword()));
        return userMapper.toDTO(userRepo.save(user));
    }

    @Override
    public String loginUser(String username, String password, String role) {

        if (role.equals("LIBRARIAN")) {
            context.getBean(DaoAuthenticationProvider.class).setUserDetailsService(librarianDetailsService);
        }
        else {
            context.getBean(DaoAuthenticationProvider.class).setUserDetailsService(userDetailsService);
        }

        Authentication authentication =  authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        if (authentication.isAuthenticated())
            return jwtService.generateToken(username);

        return "fail";
    }
}
