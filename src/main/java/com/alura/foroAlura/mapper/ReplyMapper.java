package com.alura.foroAlura.mapper;

import com.alura.foroAlura.dto.reply.ReplyResponse;
import com.alura.foroAlura.dto.user.UserResponse;
import com.alura.foroAlura.model.Reply;
import com.alura.foroAlura.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ReplyMapper {
    @Mapping(target = "user", expression = "java(userToUserResponse(reply.getUser()))")
    ReplyResponse replyToReplyResponse(Reply reply);
    default UserResponse userToUserResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }
}
