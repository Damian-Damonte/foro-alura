package com.alura.foroAlura.controller;

import com.alura.foroAlura.dto.answer.AnswerRequest;
import com.alura.foroAlura.dto.answer.AnswerResponse;
import com.alura.foroAlura.mapper.AnswerMapper;
import com.alura.foroAlura.service.AnswerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
@AllArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final AnswerMapper answerMapper;

    @GetMapping
    public ResponseEntity<List<AnswerResponse>> getAllAnswers() {
        return ResponseEntity.ok(answerService.getAllAnswers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerResponse> getAnswerById(@PathVariable Long id) {
        return ResponseEntity.ok(answerMapper.answerToAnswerResponse(answerService.getAnswerById(id)));
    }

    @PostMapping
    public ResponseEntity<AnswerResponse> saveAnswer(@RequestBody @Valid AnswerRequest answerRequest, Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED).body(answerService.saveAnswer(authentication, answerRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnswerResponse> updateAnswer(
            @PathVariable Long id, @RequestBody @Valid AnswerRequest answerRequest, Authentication authentication) {
        return ResponseEntity.ok(answerService.updateAnswer(authentication, id, answerRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long id, Authentication authentication) {
        answerService.deleteAnswer(authentication, id);
        return ResponseEntity.noContent().build();
    }
}
