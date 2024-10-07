package com.ss.lms.rest;

import com.ss.lms.dto.UserDTO;
import com.ss.lms.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class AuthRestController {

    private static final Logger log = Logger.getLogger("Auth Controller");

    private final AuthService authService;

    @Autowired
    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public UserDTO register(@RequestBody UserDTO userDTO) {
        return authService.registerUser(userDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) {
        log.info("Incoming Data: " + userDTO);
        return authService.loginUser(userDTO.getEmail(), userDTO.getPassword());
    }
}