package com.roadmap4it.infra.database.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Entity
@Table(name = "disciplines")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DisciplineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private String description;
    private int semester;

    @ElementCollection
    @CollectionTable(name = "discipline_prerequisites", joinColumns = @JoinColumn(name = "discipline_id"))
    @Column(name = "prerequisite")
    private List<String> prerequisites;

    @ElementCollection
    @CollectionTable(name = "discipline_unlocks", joinColumns = @JoinColumn(name = "discipline_id"))
    @Column(name = "unlock")
    private List<String> unlocks;

    @ElementCollection
    @CollectionTable(name = "discipline_categories", joinColumns = @JoinColumn(name = "discipline_id"))
    @Column(name = "category")
    private List<String> categories;

    @ManyToMany
    @JoinTable(
        name = "course_disciplines",
        joinColumns = @JoinColumn(name = "discipline_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<CourseEntity> courses;
}
