package com.alura.foroAlura.dto.course;

import com.alura.foroAlura.dto.common.OnlyId;

import java.util.List;

public record CourseRequest (
        String name,
        List<OnlyId> categories
){
}
