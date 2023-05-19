package com.alura.foroAlura.service;

import com.alura.foroAlura.dto.answer.AnswerRequest;
import com.alura.foroAlura.dto.answer.AnswerResponse;
import com.alura.foroAlura.dto.topic.TopicRequest;
import com.alura.foroAlura.dto.topic.TopicResponse;
import com.alura.foroAlura.dto.topic.TopicUpdate;
import com.alura.foroAlura.model.Answer;
import com.alura.foroAlura.model.Topic;

import java.util.List;

public interface AnswerService {
    Answer getAnswerById(Long id);
    List<AnswerResponse> getAllAnswers();
    AnswerResponse saveAnswer(AnswerRequest answerRequest);
    AnswerResponse updateAnswer(Long id, AnswerRequest answerRequest);
    void deleteAnswer(Long id);
}
