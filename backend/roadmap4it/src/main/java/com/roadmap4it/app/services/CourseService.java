package com.roadmap4it.app.services;

import com.roadmap4it.domain.entity.Course;
import com.roadmap4it.domain.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAllCourses();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findCourseById(id);
    }

    public Course createCourse(Course course) {
        return courseRepository.saveCourse(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteCourseById(id);
    }
}
