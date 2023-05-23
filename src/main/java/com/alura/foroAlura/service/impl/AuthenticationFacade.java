package com.alura.foroAlura.service.impl;

import com.alura.foroAlura.exception.BadRequestException;
import com.alura.foroAlura.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationFacade {
    public User getUser(Authentication authentication) {
        if (
                authentication == null
                        || !authentication.isAuthenticated()
                        || !(authentication.getPrincipal() instanceof User)) {
            throw new BadRequestException("User is not authenticated or the authentication information couldn't be retrieved");
        }

        return (User) authentication.getPrincipal();
    }
}
