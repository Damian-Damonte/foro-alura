package com.alura.foroAlura.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Category")
@Table(
        name = "categories",
        uniqueConstraints = {
                @UniqueConstraint(name = "category_name_unique", columnNames = "name")
        })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( nullable = false)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private List<Course> courses;
}
