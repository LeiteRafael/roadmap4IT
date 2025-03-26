package com.roadmap4it.domain.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {
    private long id;
    private String name;
    private String email;
    private Long courseId;
    private List<String> completedDisciplines;
}
