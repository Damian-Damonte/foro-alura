package com.alura.foroAlura.service;

import com.alura.foroAlura.dto.auth.AuthRequest;
import com.alura.foroAlura.dto.auth.AuthResponse;
import com.alura.foroAlura.dto.user.UserRequest;

public interface AuthService {
    AuthResponse register(UserRequest userRequest);
    AuthResponse authenticate(AuthRequest authRequest);
}
