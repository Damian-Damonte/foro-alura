package com.alura.foroAlura.mapper;

import com.alura.foroAlura.dto.answer.AnswerResponse;
import com.alura.foroAlura.model.Answer;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface AnswerMapper {
    AnswerResponse answerToAnswerResponse(Answer answer);
}
