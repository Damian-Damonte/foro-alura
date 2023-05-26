package com.alura.foroAlura;

import com.alura.foroAlura.dto.reply.ReplyRequest;
import com.alura.foroAlura.dto.common.OnlyId;
import com.alura.foroAlura.dto.topic.TopicRequest;
import com.alura.foroAlura.model.Category;
import com.alura.foroAlura.model.Course;
import com.alura.foroAlura.model.User;
import com.alura.foroAlura.repository.CategoryRepository;
import com.alura.foroAlura.repository.CourseRepository;
import com.alura.foroAlura.repository.UserRepository;
import com.alura.foroAlura.service.ReplyService;
import com.alura.foroAlura.service.TopicService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

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
            TopicService topicService,
            ReplyService replyService,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            User user1 = userRepository.save(User.builder()
                            .name("user 1")
                            .email("user1@gmail.com")
                            .password(passwordEncoder.encode("123456"))
                            .role(User.Role.ROLE_USER)
                            .build());
            Authentication authentication = new UsernamePasswordAuthenticationToken(user1,null, user1.getAuthorities());

            User userAdmin = userRepository.save(User.builder()
                    .name("admin")
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("admin"))
                    .role(User.Role.ROLE_ADMIN)
                    .build());

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

            topicService.saveTopic(authentication, new TopicRequest(
                    "Topico curso Intruction to Java",
                    "Mensaje del topico 1",
                        new OnlyId(1L)
                    ));

            topicService.saveTopic(authentication, new TopicRequest(
                    "Topico del curso English: first steps",
                    "Mensaje del topico 2",
                        new OnlyId(2L)
                    ));

            replyService.saveReply(authentication, new ReplyRequest(
                    "Reply 1",
                    new OnlyId(1L)
            ));

            replyService.saveReply(authentication, new ReplyRequest(
                    "Reply 2",
                    new OnlyId(1L)
            ));

            replyService.saveReply(authentication, new ReplyRequest(
                    "Reply 3",
                    new OnlyId(2L)
            ));
        };
    }
}
