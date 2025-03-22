package com.roadmap4it.app.services;

import com.roadmap4it.app.exceptions.BusinessException;
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
        boolean exists = courseRepository.existsByUniversityAndName(course.getUniversity(), course.getName());

        if (exists) {
            throw new BusinessException("Já existe um curso com o nome '"
                    + course.getName() + "' na universidade '" + course.getUniversity() + "'.");
        }
        return courseRepository.saveCourse(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteCourseById(id);
    }
}
