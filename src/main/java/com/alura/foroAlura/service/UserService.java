package com.alura.foroAlura.service;

import com.alura.foroAlura.dto.user.UserResponse;
import com.alura.foroAlura.model.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);
    List<UserResponse> getAllUsers();
    void deleteUser(Long id);
}
