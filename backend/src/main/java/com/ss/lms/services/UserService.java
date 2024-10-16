package com.ss.lms.services;

import com.ss.lms.dto.UserDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface UserService {

    List<UserDTO> getAll();

    UserDTO getById(int id);

    UserDTO create(@Valid UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    void deleteById(int id);

    UserDTO getSelfDetails();
}
