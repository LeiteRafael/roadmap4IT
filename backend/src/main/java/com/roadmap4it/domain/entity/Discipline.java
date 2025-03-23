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
public class Discipline {
    private String code;
    private String name;
    private String description;
    private int semester;
    private List<String> prerequisites;
    private List<String> unlocks;
    private List<String> categories;
}
