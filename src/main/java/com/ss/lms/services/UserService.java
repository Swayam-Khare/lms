package com.ss.lms.services;

import com.ss.lms.dto.UserDTO;
import com.ss.lms.entity.User;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    User findById(int id);

    User save(User user);

    void deleteById(int id);
}
