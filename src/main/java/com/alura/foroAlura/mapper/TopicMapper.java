package com.alura.foroAlura.mapper;

import com.alura.foroAlura.dto.common.OnlyId;
import com.alura.foroAlura.dto.topic.TopicRequest;
import com.alura.foroAlura.dto.topic.TopicResponse;
import com.alura.foroAlura.model.Course;
import com.alura.foroAlura.model.Topic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface TopicMapper {
    @Mapping(
            target = "course",
            expression = "java(onlyIdToCourse(topicRequest.course()))")
    @Mapping(target = "creationDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "status", expression = "java(com.alura.foroAlura.model.Topic.TopicStatus.UNANSWERED)")
    Topic topicRequestToTopic(TopicRequest topicRequest);

    @Mapping(target = "course", expression = "java(courseToCourseDTO(topic.getCourse()))")
    TopicResponse topicToTopicResponse(Topic topic);

//    Probar ponerlo directo en el valor de expression
    default Course onlyIdToCourse(OnlyId topicId) {
        return Course.builder().id(topicId.id()).build();
    }
    default TopicResponse.CourseDTO courseToCourseDTO(Course course) {
        return new TopicResponse.CourseDTO(course.getId(), course.getName());
    }
}
