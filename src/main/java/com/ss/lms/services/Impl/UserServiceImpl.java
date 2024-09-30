package com.ss.lms.services.Impl;

import com.ss.lms.dto.UserDTO;
import com.ss.lms.entity.User;
import com.ss.lms.mapper.UserMapper;
import com.ss.lms.repository.UserRepository;
import com.ss.lms.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = Logger.getLogger("User Service Impl");

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> getAll() {

        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @Override
    public UserDTO getById(int id) {

        return userMapper.toDTO(Objects.requireNonNull(
                userRepository
                        .findById(id)
                        .orElse(null),
        "No user found with id: " + id
        ));
    }

    @Override
    public UserDTO create(UserDTO userDTO) {

        User user = userRepository.save(
                userMapper.toEntity(userDTO)
        );
        return userMapper.toDTO(user);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {

        User user = userRepository
                .findById(userDTO.getId())
                .orElse(null);

        if (user == null) {
            // TODO: throw custom exception "user not found"
            return null;
        }

        user = userMapper.toEntity(userDTO);

        return userMapper.toDTO(
                userRepository.save(user)
        );
    }

    @Override
    public void deleteById(int id) {

        User user = userRepository
                .findById(id)
                .orElse(null);

        if (user == null) {
            // TODO: throw custom exception "user not found"
            return;
        }

        userRepository.deleteById(id);
    }
}
