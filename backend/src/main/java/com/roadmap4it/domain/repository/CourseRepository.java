package com.roadmap4it.domain.repository;

import com.roadmap4it.domain.entity.Course;
import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    List<Course> findAllCourses();

    Optional<Course> findCourseById(Long id);

    Course saveCourse(Course course);

    void deleteCourseById(Long id);

    boolean existsByUniversityAndName(String university, String name);
}
