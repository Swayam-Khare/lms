package com.ss.lms.services;

import com.ss.lms.dto.UserDTO;
import com.ss.lms.dto.UserInfoResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface UserService {

    List<UserDTO> getAll();

    UserDTO getById(int id);

    UserDTO create(@Valid UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    void deleteById(int id);

    UserDTO getSelfDetails();

    List<UserDTO> searchUsers(String searchText);

    UserInfoResponse getInfo();
}
