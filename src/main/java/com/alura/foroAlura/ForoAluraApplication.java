package com.alura.foroAlura;

import com.alura.foroAlura.dto.common.OnlyId;
import com.alura.foroAlura.dto.topic.TopicRequest;
import com.alura.foroAlura.dto.topic.TopicResponse;
import com.alura.foroAlura.model.Category;
import com.alura.foroAlura.model.Course;
import com.alura.foroAlura.model.Topic;
import com.alura.foroAlura.repository.CategoryRepository;
import com.alura.foroAlura.repository.CourseRepository;
import com.alura.foroAlura.service.TopicService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ForoAluraApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForoAluraApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            CategoryRepository categoryRepository,
            CourseRepository courseRepository,
            TopicService topicService
    ) {
        return args -> {
            Category programing = categoryRepository.save(Category.builder().name("Programing").build());
            Category english = categoryRepository.save(Category.builder().name("English").build());
            Category java = categoryRepository.save(Category.builder().name("Java").build());

            courseRepository.save(Course.builder()
                            .name("Introduction to Java")
                            .categories(List.of(programing, java))
                            .build());

            courseRepository.save(Course.builder()
                    .name("English: first steps")
                    .categories(List.of(english))
                    .build());

            TopicResponse topic1 = topicService.saveTopic(new TopicRequest(
                    "Topico curso Intruction to Java",
                    "Mensaje del topico 1",
                        new OnlyId(1L)
                    ));

            TopicResponse topic2 = topicService.saveTopic(new TopicRequest(
                    "Topico del curso English: first steps",
                    "Mensaje del topico 2",
                        new OnlyId(2L)
                    ));
        };
    }
}
