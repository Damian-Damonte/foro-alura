package com.alura.foroAlura.service.impl;

import com.alura.foroAlura.dto.reply.ReplyRequest;
import com.alura.foroAlura.dto.reply.ReplyResponse;
import com.alura.foroAlura.exception.ForbiddenException;
import com.alura.foroAlura.exception.NotFoundException;
import com.alura.foroAlura.mapper.ReplyMapper;
import com.alura.foroAlura.model.Reply;
import com.alura.foroAlura.model.Topic;
import com.alura.foroAlura.model.User;
import com.alura.foroAlura.repository.ReplyRepository;
import com.alura.foroAlura.service.ReplyService;
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
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;
    private final ReplyMapper replyMapper;
    private final TopicService topicService;
    private final AuthenticationFacade authenticationFacade;


    @Override
    public Reply getReplyById(Long id) {
        return replyRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Reply with id " + id + " not found")
        );
    }

    @Override
    public List<ReplyResponse> getAllReplies() {
        return replyRepository.findAll().stream().map(replyMapper::replyToReplyResponse).toList();
    }

    @Override
    @Transactional
    public ReplyResponse saveReply(Authentication authentication, ReplyRequest replyRequest) {
        Topic topic = topicService.getTopicById(replyRequest.topic().id());
        Reply reply = Reply.builder()
                .message(replyRequest.message())
                .creationDate(LocalDateTime.now())
                .solution(false)
                .topic(topic)
                .user(authenticationFacade.getUser(authentication))
                .build();

        return replyMapper.replyToReplyResponse(replyRepository.save(reply));
    }

    @Override
    @Transactional
    public ReplyResponse updateReply(Authentication authentication, Long id, ReplyRequest replyRequest) {
        Reply reply = getReplyById(id);
        isReplyOwnedByUser(authentication, reply.getUser().getId());
        Topic topic = topicService.getTopicById(replyRequest.topic().id());
        reply.setMessage(replyRequest.message());
        reply.setTopic(topic);

        return replyMapper.replyToReplyResponse(reply);
    }

    @Override
    public void deleteReply(Authentication authentication, Long id) {
        Reply reply = getReplyById(id);
        isReplyOwnedByUser(authentication, reply.getUser().getId());
        replyRepository.deleteById(id);
    }

    private void isReplyOwnedByUser(Authentication authentication, Long id) {
        User user = authenticationFacade.getUser(authentication);
        if(!(Objects.equals(user.getId(), id)))
            throw new ForbiddenException("You are not authorized to modify the topic as it does not belong to you");
    }
}
