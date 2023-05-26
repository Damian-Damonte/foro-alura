package com.alura.foroAlura.service.impl;

import com.alura.foroAlura.dto.topic.TopicRequest;
import com.alura.foroAlura.dto.topic.TopicResponse;
import com.alura.foroAlura.dto.topic.TopicUpdate;
import com.alura.foroAlura.exception.BadRequestException;
import com.alura.foroAlura.exception.ForbiddenException;
import com.alura.foroAlura.exception.NotFoundException;
import com.alura.foroAlura.mapper.TopicMapper;
import com.alura.foroAlura.model.Reply;
import com.alura.foroAlura.model.Course;
import com.alura.foroAlura.model.Topic;
import com.alura.foroAlura.repository.TopicRepository;
import com.alura.foroAlura.service.CourseService;
import com.alura.foroAlura.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alura.foroAlura.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TopicServiceImp implements TopicService {
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;
    private final CourseService courseService;
    private final AuthenticationFacade authenticationFacade;


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
    public TopicResponse saveTopic(Authentication authentication, TopicRequest topicRequest) {
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
                .replies(new ArrayList<>())
                .user(authenticationFacade.getUser(authentication))
                .build());

        return topicMapper.topicToTopicResponse(topic);
    }

    @Override
    @Transactional
    public TopicResponse updateTopic(Authentication authentication, Long id, TopicUpdate topicUpdate) {
        Topic topic = getTopicById(id);
        isTopicOwnedByUser(authentication, topic.getUser().getId());
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
    public void deleteTopic(Authentication authentication, Long id) {
        Topic topic = getTopicById(id);
        isTopicOwnedByUser(authentication, topic.getUser().getId());
        topicRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void topicSolution(Authentication authentication, Long topicId, Long answerId) {
        Topic topic = getTopicById(topicId);
        isTopicOwnedByUser(authentication, topic.getUser().getId());
        Optional<Reply> answer = topic.getReplies().stream().filter(
                ans -> Objects.equals(ans.getId(), answerId)).findFirst();
        if(answer.isEmpty())
            throw new BadRequestException("Reply with id " + answerId + " not found in topic with id " + topicId);

        answer.get().setSolution(!answer.get().isSolution());
    }

    private void isTopicOwnedByUser(Authentication authentication, Long id) {
        User user = authenticationFacade.getUser(authentication);
        System.out.println("User id: " + user.getId() + " ---- id recibido: " + id);
        if(!(Objects.equals(user.getId(), id)))
            throw new ForbiddenException("You are not authorized to modify the topic as it does not belong to you");
    }
}
