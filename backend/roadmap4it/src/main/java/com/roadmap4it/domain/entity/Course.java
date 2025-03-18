package com.roadmap4it.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Course {
    private String university;
    private String name;
    private int duration;
    private String area;
    private List<String> disciplines;
}
