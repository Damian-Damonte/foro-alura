package com.alura.foroAlura.service.impl;

import com.alura.foroAlura.dto.user.UserResponse;
import com.alura.foroAlura.exception.NotFoundException;
import com.alura.foroAlura.mapper.UserMapper;
import com.alura.foroAlura.model.User;
import com.alura.foroAlura.repository.UserRepository;
import com.alura.foroAlura.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User with id " + id + " not found")
        );
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::userToUserResponse).toList();
    }

    @Override
    public void deleteUser(Long id) {
        getUserById(id);
        userRepository.deleteById(id);
    }
}
