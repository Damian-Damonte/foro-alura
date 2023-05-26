package com.alura.foroAlura.service;

import com.alura.foroAlura.dto.reply.ReplyRequest;
import com.alura.foroAlura.dto.reply.ReplyResponse;
import com.alura.foroAlura.model.Reply;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ReplyService {
    Reply getReplyById(Long id);
    List<ReplyResponse> getAllReplies();
    ReplyResponse saveReply(Authentication authentication, ReplyRequest replyRequest);
    ReplyResponse updateReply(Authentication authentication, Long id, ReplyRequest replyRequest);
    void deleteReply(Authentication authentication, Long id);
}
