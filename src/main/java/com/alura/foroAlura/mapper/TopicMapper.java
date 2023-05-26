package com.alura.foroAlura.mapper;

import com.alura.foroAlura.dto.topic.TopicResponse;
import com.alura.foroAlura.dto.user.UserResponse;
import com.alura.foroAlura.model.Reply;
import com.alura.foroAlura.model.Course;
import com.alura.foroAlura.model.Topic;
import com.alura.foroAlura.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface TopicMapper {

    @Mapping(target = "course", expression = "java(courseToCourseDTO(topic.getCourse()))")
    @Mapping(target = "replies", expression = "java(replyToReplyDTO(topic.getReplies()))")
    @Mapping(target = "user", expression = "java(userToUserResponse(topic.getUser()))")
    TopicResponse topicToTopicResponse(Topic topic);


    default TopicResponse.CourseDTO courseToCourseDTO(Course course) {
        return new TopicResponse.CourseDTO(course.getId(), course.getName());
    }

    default List<TopicResponse.ReplyDTO> replyToReplyDTO(List<Reply> replies) {
        return replies.stream().map(ans-> new TopicResponse.ReplyDTO(
                ans.getId(), ans.getMessage(), ans.getCreationDate(), ans.isSolution())).toList();
    }
    default UserResponse userToUserResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }
}
