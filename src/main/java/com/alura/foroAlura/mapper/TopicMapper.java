package com.alura.foroAlura.mapper;

import com.alura.foroAlura.dto.topic.TopicResponse;
import com.alura.foroAlura.model.Answer;
import com.alura.foroAlura.model.Course;
import com.alura.foroAlura.model.Topic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface TopicMapper {

    @Mapping(target = "course", expression = "java(courseToCourseDTO(topic.getCourse()))")
    @Mapping(target = "answers", expression = "java(answerToAnswerDTO(topic.getAnswers()))")
    TopicResponse topicToTopicResponse(Topic topic);


    default TopicResponse.CourseDTO courseToCourseDTO(Course course) {
        return new TopicResponse.CourseDTO(course.getId(), course.getName());
    }

    default List<TopicResponse.AnswerDTO> answerToAnswerDTO(List<Answer> answers) {
        return answers.stream().map(ans-> new TopicResponse.AnswerDTO(
                ans.getId(), ans.getMessage(), ans.getCreationDate(), ans.isSolution())).toList();
    }
}
