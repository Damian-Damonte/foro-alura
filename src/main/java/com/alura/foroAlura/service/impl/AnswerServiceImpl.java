package com.alura.foroAlura.service.impl;

import com.alura.foroAlura.dto.answer.AnswerRequest;
import com.alura.foroAlura.dto.answer.AnswerResponse;
import com.alura.foroAlura.exception.ForbiddenException;
import com.alura.foroAlura.exception.NotFoundException;
import com.alura.foroAlura.mapper.AnswerMapper;
import com.alura.foroAlura.model.Answer;
import com.alura.foroAlura.model.Topic;
import com.alura.foroAlura.model.User;
import com.alura.foroAlura.repository.AnswerRepository;
import com.alura.foroAlura.service.AnswerService;
import com.alura.foroAlura.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;
    private final TopicService topicService;
    private final AuthenticationFacade authenticationFacade;


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
    public AnswerResponse saveAnswer(Authentication authentication, AnswerRequest answerRequest) {
        Topic topic = topicService.getTopicById(answerRequest.topic().id());
        Answer answer = Answer.builder()
                .message(answerRequest.message())
                .creationDate(LocalDateTime.now())
                .solution(false)
                .topic(topic)
                .user(authenticationFacade.getUser(authentication))
                .build();

        return answerMapper.answerToAnswerResponse(answerRepository.save(answer));
    }

    @Override
    @Transactional
    public AnswerResponse updateAnswer(Authentication authentication, Long id, AnswerRequest answerRequest) {
        Answer answer = getAnswerById(id);
        isAnswerOwnedByUser(authentication, answer.getUser().getId());
        Topic topic = topicService.getTopicById(answerRequest.topic().id());
        answer.setMessage(answerRequest.message());
        answer.setTopic(topic);

        return answerMapper.answerToAnswerResponse(answer);
    }

    @Override
    public void deleteAnswer(Authentication authentication, Long id) {
        Answer answer = getAnswerById(id);
        isAnswerOwnedByUser(authentication, answer.getUser().getId());
        answerRepository.deleteById(id);
    }

    private void isAnswerOwnedByUser(Authentication authentication, Long id) {
        User user = authenticationFacade.getUser(authentication);
        if(!(Objects.equals(user.getId(), id)))
            throw new ForbiddenException("You are not authorized to modify the topic as it does not belong to you");
    }
}
