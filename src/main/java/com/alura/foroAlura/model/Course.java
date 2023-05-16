package com.alura.foroAlura.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Course")
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( nullable = false)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @ManyToMany
    @JoinTable(
            name = "courses_categories",
            joinColumns = @JoinColumn(
                 name = "course_id",
                 foreignKey = @ForeignKey(name = "course_category_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "category_id",
                    foreignKey = @ForeignKey(name = "category_course_id")
            )
    )
    private List<Category> categories;
}
