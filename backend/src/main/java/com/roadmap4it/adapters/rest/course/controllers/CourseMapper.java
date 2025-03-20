package com.roadmap4it.adapters.rest.course.controllers;

import com.roadmap4it.domain.entity.Course;
import com.roadmap4it.infra.database.model.CourseEntity;

public class CourseMapper {

    public static CourseEntity toEntity(Course course) {
        return CourseEntity.builder()
                .university(course.getUniversity())
                .name(course.getName())
                .duration(course.getDuration())
                .area(course.getArea())
                .disciplines(course.getDisciplines())
                .build(); 
    }

    public static Course toDomain(CourseEntity entity) {
        return new Course(
                entity.getUniversity(),
                entity.getName(),
                entity.getDuration(),
                entity.getArea(),
                entity.getDisciplines()
        );
    }
}
