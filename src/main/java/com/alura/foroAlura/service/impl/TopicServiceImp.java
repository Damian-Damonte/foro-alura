package com.alura.foroAlura.service.impl;

import com.alura.foroAlura.dto.topic.TopicRequest;
import com.alura.foroAlura.dto.topic.TopicResponse;
import com.alura.foroAlura.dto.topic.TopicUpdate;
import com.alura.foroAlura.exception.BadRequestException;
import com.alura.foroAlura.exception.NotFoundException;
import com.alura.foroAlura.mapper.TopicMapper;
import com.alura.foroAlura.model.Course;
import com.alura.foroAlura.model.Topic;
import com.alura.foroAlura.repository.TopicRepository;
import com.alura.foroAlura.service.CourseService;
import com.alura.foroAlura.service.TopicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TopicServiceImp implements TopicService {
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;
    private final CourseService courseService;

    public TopicServiceImp(TopicRepository topicRepository, TopicMapper topicMapper, CourseService courseService) {
        this.topicRepository = topicRepository;
        this.topicMapper = topicMapper;
        this.courseService = courseService;
    }

    @Override
    public Topic getTopicById(Long id) {
        return topicRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Topic with id " + id + " not found")
        );
    }

    @Override
    public List<TopicResponse> getAllTopics() {
        return topicRepository.findAll().stream().map(topicMapper::topicToTopicResponse).toList();
    }

    @Override
    @Transactional
    public TopicResponse saveTopic(TopicRequest topicRequest) {
        Course course = courseService.getCourseById(topicRequest.course().id());
        boolean topicAlredyExists = topicRepository.findByTitleAndMessageAndCourseId(
                topicRequest.title(), topicRequest.message(), course.getId()).isPresent();
        if(topicAlredyExists)
            throw new BadRequestException("The topic with that title and message already exists in the course with ID " + topicRequest.course().id());

        Topic topic = topicRepository.save(Topic.builder()
                .title(topicRequest.title())
                .message(topicRequest.message())
                .creationDate(LocalDateTime.now())
                .status(Topic.TopicStatus.UNANSWERED)
                .course(course)
                .answers(new ArrayList<>())
                .build());

        return topicMapper.topicToTopicResponse(topic);
    }

    @Override
    @Transactional
    public TopicResponse updateTopic(Long id, TopicUpdate topicUpdate) {
        Topic topic = getTopicById(id);
        Course course = courseService.getCourseById(topicUpdate.course().id());
        Topic topicByAtributtes = topicRepository.findByTitleAndMessageAndCourseId(
                topicUpdate.title(), topicUpdate.message(), topicUpdate.course().id()
        ).orElse(null);
        if(topicByAtributtes != null && !(Objects.equals(topicByAtributtes.getId(), id)))
            throw new BadRequestException("The topic with that title and message already exists in the course with ID " + topicUpdate.course().id());

        topic.setTitle(topicUpdate.title());
        topic.setMessage(topicUpdate.message());
        topic.setStatus(Topic.TopicStatus.valueOf(topicUpdate.status()));
        topic.setCourse(course);

        return topicMapper.topicToTopicResponse(topic);
    }

    @Override
    @Transactional
    public void deleteTopic(Long id) {
        getTopicById(id);
        topicRepository.deleteById(id);
    }
}
