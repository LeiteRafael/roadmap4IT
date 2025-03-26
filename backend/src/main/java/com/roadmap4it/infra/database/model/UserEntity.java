package com.roadmap4it.infra.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long courseId;

    @ElementCollection
    @CollectionTable(name = "user_completed_disciplines", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "discipline_code")
    private List<String> completedDisciplines;
}
