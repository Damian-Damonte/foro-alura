package com.alura.foroAlura.service.impl;

import com.alura.foroAlura.dto.answer.AnswerRequest;
import com.alura.foroAlura.dto.answer.AnswerResponse;
import com.alura.foroAlura.exception.NotFoundException;
import com.alura.foroAlura.mapper.AnswerMapper;
import com.alura.foroAlura.model.Answer;
import com.alura.foroAlura.model.Topic;
import com.alura.foroAlura.repository.AnswerRepository;
import com.alura.foroAlura.service.AnswerService;
import com.alura.foroAlura.service.TopicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;
    private final TopicService topicService;

    public AnswerServiceImpl(AnswerRepository answerRepository, AnswerMapper answerMapper, TopicService topicService) {
        this.answerRepository = answerRepository;
        this.answerMapper = answerMapper;
        this.topicService = topicService;
    }

    @Override
    public Answer getAnswerById(Long id) {
        return answerRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Answer with id " + id + " not found")
        );
    }

    @Override
    public List<AnswerResponse> getAllAnswers() {
        return answerRepository.findAll().stream().map(answerMapper::answerToAnswerResponse).toList();
    }

    @Override
    @Transactional
    public AnswerResponse saveAnswer(AnswerRequest answerRequest) {
        Topic topic = topicService.geTopicById(answerRequest.topic().id());
        Answer answer = Answer.builder()
                .message(answerRequest.message())
                .creationDate(LocalDateTime.now())
                .solution(false)
                .topic(topic)
                .build();

        return answerMapper.answerToAnswerResponse(answerRepository.save(answer));
    }

    @Override
    @Transactional
    public AnswerResponse updateAnswer(Long id, AnswerRequest answerRequest) {
        Topic topic = topicService.geTopicById(answerRequest.topic().id());
        Answer answer = getAnswerById(id);
        answer.setMessage(answerRequest.message());
        answer.setTopic(topic);

        return answerMapper.answerToAnswerResponse(answer);
    }

    @Override
    public void deleteAnswer(Long id) {
        getAnswerById(id);
        answerRepository.deleteById(id);
    }
}
