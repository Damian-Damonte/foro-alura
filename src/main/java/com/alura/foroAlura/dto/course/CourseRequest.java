package com.alura.foroAlura.dto.course;

import java.util.List;

public record CourseRequest (
        String name,
        List<Long> categoriesIds
){
}
