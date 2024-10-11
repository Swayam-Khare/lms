package com.ss.lms.rest;

import com.ss.lms.dto.LoginRequest;
import com.ss.lms.dto.UserDTO;
import com.ss.lms.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthRestController {

    private static final Logger log = Logger.getLogger("Auth Controller");

    private final AuthService authService;

    @Autowired
    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public UserDTO register(@Valid @RequestBody UserDTO userDTO) {
        log.info("In register method");
        return authService.registerUser(userDTO);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("Incoming Data: " + loginRequest);
        return authService.loginUser(loginRequest.getEmail(), loginRequest.getPassword(), loginRequest.getRole());
    }
}