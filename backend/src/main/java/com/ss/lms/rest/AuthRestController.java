package com.ss.lms.rest;

import com.ss.lms.dto.LibrarianDTO;
import com.ss.lms.dto.LoginRequest;
import com.ss.lms.dto.UserDTO;
import com.ss.lms.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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

    @PostMapping("/register/user")
    public UserDTO registerUser(@Valid @RequestBody UserDTO userDTO) {
        return authService.registerUser(userDTO);
    }

    @PostMapping("/register/librarian")
    public LibrarianDTO registerLibrarian(@Valid @RequestBody LibrarianDTO librarianDTO) {
        return authService.registerLibrarian(librarianDTO);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse res) {
        String token = authService.loginUser(
                loginRequest.getEmail(),
                loginRequest.getPassword(),
                loginRequest.getRole()
        );

        Cookie cookie = new Cookie("jwtAuth", token);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60);

        res.addCookie(cookie);

        return token;
    }
}