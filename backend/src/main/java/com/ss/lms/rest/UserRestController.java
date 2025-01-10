package com.ss.lms.rest;

import com.ss.lms.dto.UserDTO;
import com.ss.lms.dto.UserInfoResponse;
import com.ss.lms.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserRestController {

    private static final Logger log = LoggerFactory.getLogger(UserRestController.class.getSimpleName());

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<UserDTO> getAll(@RequestParam(name = "search", required = false) String search) {
//        log.info("Searching users with search string: {}", search);
        if (search != null) {
            return userService.searchUsers(search);
        }

        return userService.getAll();
    }

    @GetMapping("/info")
    public UserInfoResponse getInfo() {
        return userService.getInfo();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable int id) {
        return userService.getById(id);
    }

    @GetMapping("/me")
    public UserDTO getSelfDetails() {
        return userService.getSelfDetails();
    }

    @PostMapping("/")
    public UserDTO create(@RequestBody UserDTO userDTO) {
        userDTO.setPassword(userDTO.getEmail() + "LMS123");
        return userService.create(userDTO);
    }

    @PutMapping("/")
    public UserDTO update(@Valid @RequestBody UserDTO userDTO) {
        return userService.update(userDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        userService.deleteById(id);
    }
}
