package com.ss.lms.services;

import com.ss.lms.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(int id);

    User save(User user);

    void deleteById(int id);
}
