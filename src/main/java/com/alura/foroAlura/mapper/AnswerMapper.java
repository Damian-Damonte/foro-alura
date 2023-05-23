package com.alura.foroAlura.mapper;

import com.alura.foroAlura.dto.answer.AnswerResponse;
import com.alura.foroAlura.dto.user.UserResponse;
import com.alura.foroAlura.model.Answer;
import com.alura.foroAlura.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface AnswerMapper {
    @Mapping(target = "user", expression = "java(userToUserResponse(answer.getUser()))")
    AnswerResponse answerToAnswerResponse(Answer answer);
    default UserResponse userToUserResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }
}
