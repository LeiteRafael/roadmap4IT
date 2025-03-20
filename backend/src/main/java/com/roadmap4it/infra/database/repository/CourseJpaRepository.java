package com.roadmap4it.infra.database.repository;

import com.roadmap4it.adapters.rest.course.controllers.CourseMapper;
import com.roadmap4it.domain.entity.Course;
import com.roadmap4it.domain.repository.CourseRepository;
import com.roadmap4it.infra.database.model.CourseEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface CourseJpaRepository extends JpaRepository<CourseEntity, Long>, CourseRepository {

    default List<Course> findAllCourses() {
        return findAll().stream()
                .map(CourseMapper::toDomain)
                .collect(Collectors.toList());
    }

    default Optional<Course> findCourseById(Long id) {
        return findById(id).map(CourseMapper::toDomain);
    }

    default Course saveCourse(Course course) {
        CourseEntity entity = CourseMapper.toEntity(course);
        return CourseMapper.toDomain(save(entity));
    }

    default void deleteCourseById(Long id) {
        deleteById(id);
    }
}
