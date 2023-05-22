package com.alura.foroAlura.service.impl;

import com.alura.foroAlura.dto.auth.AuthRequest;
import com.alura.foroAlura.dto.auth.AuthResponse;
import com.alura.foroAlura.dto.user.UserRequest;
import com.alura.foroAlura.exception.BadRequestException;
import com.alura.foroAlura.model.User;
import com.alura.foroAlura.repository.UserRepository;
import com.alura.foroAlura.security.JwtService;
import com.alura.foroAlura.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(JwtService jwtService, AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse register(UserRequest userRequest) {
        String email = userRequest.email();
        if(userRepository.findByEmail(email).isPresent())
            throw new BadRequestException("Email '" + email + "' is already in use");

        User user = userRepository.save(User.builder()
                        .name(userRequest.name())
                        .email(userRequest.email())
                        .password(passwordEncoder.encode(userRequest.password()))
                        .role(User.Role.ROLE_USER)
                        .build()
        );

//        String jwtToken = jwtService.generateToken(getClaims(user), user);
        return new AuthResponse(jwtService.generateToken(getClaims(user), user));
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.email(),
                        authRequest.password()
                )
        );
        User user = (User) auth.getPrincipal();

//        String jwtToken = jwtService.generateToken(getClaims(user), user);

        return new AuthResponse(jwtService.generateToken(getClaims(user), user));
    }

    private Map<String, Object> getClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("nombre", user.getName());
        claims.put("email", user.getEmail());
        claims.put("rol", user.getRole().name().substring(5));
        return claims;
    }
}
