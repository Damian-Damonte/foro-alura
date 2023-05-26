package com.alura.foroAlura.mapper;

import com.alura.foroAlura.dto.user.UserResponse;
import com.alura.foroAlura.model.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    UserResponse userToUserResponse(User user);
}
