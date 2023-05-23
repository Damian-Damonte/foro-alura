package com.alura.foroAlura.controller;

import com.alura.foroAlura.dto.topic.TopicRequest;
import com.alura.foroAlura.dto.topic.TopicResponse;
import com.alura.foroAlura.dto.topic.TopicUpdate;
import com.alura.foroAlura.mapper.TopicMapper;
import com.alura.foroAlura.service.TopicService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
@AllArgsConstructor
public class TopicController {
    private final TopicService topicService;
    private final TopicMapper topicMapper;

    @GetMapping
    public ResponseEntity<List<TopicResponse>> getAllTopics() {
        return ResponseEntity.ok(topicService.getAllTopics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponse> getTopicById(@PathVariable Long id) {
        return ResponseEntity.ok(topicMapper.topicToTopicResponse(topicService.getTopicById(id)));
    }

    @PostMapping
    public ResponseEntity<TopicResponse> saveTopic(@RequestBody @Valid TopicRequest topicRequest,
                                                   Authentication authentication
            ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(topicService.saveTopic(authentication, topicRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicResponse> updateTopic(@PathVariable Long id,
                                                     @RequestBody @Valid TopicUpdate topicUpdate,
                                                     Authentication authentication) {
        return ResponseEntity.ok(topicService.updateTopic(authentication, id, topicUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id, Authentication authentication) {
        topicService.deleteTopic(authentication, id);
        return ResponseEntity.noContent().build();
    }
}
