package com.alura.foroAlura.mapper;

import com.alura.foroAlura.dto.topic.TopicResponse;
import com.alura.foroAlura.model.Course;
import com.alura.foroAlura.model.Topic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface TopicMapper {

    @Mapping(target = "course", expression = "java(courseToCourseDTO(topic.getCourse()))")
    TopicResponse topicToTopicResponse(Topic topic);


    default TopicResponse.CourseDTO courseToCourseDTO(Course course) {
        return new TopicResponse.CourseDTO(course.getId(), course.getName());
    }
}
