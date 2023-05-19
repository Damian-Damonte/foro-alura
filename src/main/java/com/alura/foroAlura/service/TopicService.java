package com.alura.foroAlura.service;

import com.alura.foroAlura.dto.topic.TopicRequest;
import com.alura.foroAlura.dto.topic.TopicResponse;
import com.alura.foroAlura.dto.topic.TopicUpdate;
import com.alura.foroAlura.model.Topic;

import java.util.List;

public interface TopicService {
    Topic geTopicById(Long id);
    List<TopicResponse> getAllTopics();
    TopicResponse saveTopic(TopicRequest topicRequest);
    TopicResponse updateTopic(Long id, TopicUpdate topicUpdate);
    void deleteTopic(Long id);
}
