package com.roadmap4it.infra.database.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;

import java.util.List;


@Entity
@Table(name = "courses")
@Getter
@Setter
@ToString
@Builder
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String university;
    private String name;
    private int duration;
    private String area;

    @ElementCollection
    @CollectionTable(name = "course_disciplines", joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "discipline")
    private List<String> disciplines;
}
