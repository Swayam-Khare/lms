package com.ss.lms.rest;

import com.ss.lms.dto.UserDTO;
import com.ss.lms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable int id) {
        return userService.getById(id);
    }

    @PostMapping("/")
    public UserDTO create(@RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @PutMapping("/")
    public UserDTO update(@RequestBody UserDTO userDTO) {
        return userService.update(userDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        userService.deleteById(id);
    }
}
