package com.alura.foroAlura.controller;

import com.alura.foroAlura.dto.reply.ReplyRequest;
import com.alura.foroAlura.dto.reply.ReplyResponse;
import com.alura.foroAlura.mapper.ReplyMapper;
import com.alura.foroAlura.service.ReplyService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/replies")
@AllArgsConstructor
public class ReplyController {
    private final ReplyService replyService;
    private final ReplyMapper replyMapper;

    @GetMapping
    public ResponseEntity<List<ReplyResponse>> getAllReplies() {
        return ResponseEntity.ok(replyService.getAllReplies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReplyResponse> getReplyById(@PathVariable Long id) {
        return ResponseEntity.ok(replyMapper.replyToReplyResponse(replyService.getReplyById(id)));
    }

    @PostMapping
    public ResponseEntity<ReplyResponse> saveReply(@RequestBody @Valid ReplyRequest replyRequest, Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED).body(replyService.saveReply(authentication, replyRequest));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Only the owner of the reply can modify it"
    )
    public ResponseEntity<ReplyResponse> updateReply(
            @PathVariable Long id, @RequestBody @Valid ReplyRequest replyRequest, Authentication authentication) {
        return ResponseEntity.ok(replyService.updateReply(authentication, id, replyRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Only the owner of the reply can delete it"
    )
    public ResponseEntity<Void> deleteReply(@PathVariable Long id, Authentication authentication) {
        replyService.deleteReply(authentication, id);
        return ResponseEntity.noContent().build();
    }
}
