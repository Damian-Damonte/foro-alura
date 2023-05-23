package com.alura.foroAlura.service;

import com.alura.foroAlura.dto.topic.TopicRequest;
import com.alura.foroAlura.dto.topic.TopicResponse;
import com.alura.foroAlura.dto.topic.TopicUpdate;
import com.alura.foroAlura.model.Topic;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TopicService {
    Topic getTopicById(Long id);
    List<TopicResponse> getAllTopics();
    TopicResponse saveTopic(Authentication authentication, TopicRequest topicRequest);
    TopicResponse updateTopic(Authentication authentication, Long id, TopicUpdate topicUpdate);
    void deleteTopic(Authentication authentication, Long id);
    void topicSolution(Authentication authentication, Long topicId, Long answerId);
}
