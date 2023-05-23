package com.alura.foroAlura.service;

import com.alura.foroAlura.dto.answer.AnswerRequest;
import com.alura.foroAlura.dto.answer.AnswerResponse;
import com.alura.foroAlura.dto.topic.TopicRequest;
import com.alura.foroAlura.dto.topic.TopicResponse;
import com.alura.foroAlura.dto.topic.TopicUpdate;
import com.alura.foroAlura.model.Answer;
import com.alura.foroAlura.model.Topic;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AnswerService {
    Answer getAnswerById(Long id);
    List<AnswerResponse> getAllAnswers();
    AnswerResponse saveAnswer(Authentication authentication, AnswerRequest answerRequest);
    AnswerResponse updateAnswer(Authentication authentication, Long id, AnswerRequest answerRequest);
    void deleteAnswer(Authentication authentication, Long id);
}
