package com.alura.foroAlura.controller;

import com.alura.foroAlura.dto.topic.TopicRequest;
import com.alura.foroAlura.dto.topic.TopicResponse;
import com.alura.foroAlura.dto.topic.TopicUpdate;
import com.alura.foroAlura.mapper.TopicMapper;
import com.alura.foroAlura.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
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

    @GetMapping("/{topicId}/solution/{answerId}")
    @Operation(
            summary = "Only the owner of the topic can mark a reply as the solution"
    )
    public ResponseEntity<Void> topicSoluction(Authentication authentication,
                                               @PathVariable Long topicId, @PathVariable Long answerId){
        topicService.topicSolution(authentication, topicId, answerId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @Operation(
            summary = "There cannot be another topic with the same title and message in the same course"
    )
    public ResponseEntity<TopicResponse> saveTopic(@RequestBody @Valid TopicRequest topicRequest,
                                                   Authentication authentication
            ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(topicService.saveTopic(authentication, topicRequest));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Only the owner of the topic can modify it"
    )
    public ResponseEntity<TopicResponse> updateTopic(@PathVariable Long id,
                                                     @RequestBody @Valid TopicUpdate topicUpdate,
                                                     Authentication authentication) {
        return ResponseEntity.ok(topicService.updateTopic(authentication, id, topicUpdate));
    }

    @DeleteMapping("/{id}")
    @Operation(
            description = "When deleting the topic, all its replies will be deleted as well"
    )
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id, Authentication authentication) {
        topicService.deleteTopic(authentication, id);
        return ResponseEntity.noContent().build();
    }
}
