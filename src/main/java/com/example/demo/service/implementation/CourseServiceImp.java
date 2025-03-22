package com.example.demo.service.implementation;

import com.example.demo.exception.GlobalNotFoundException;
import com.example.demo.model.dto.request.CourseRequest;
import com.example.demo.model.entity.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImp implements CourseService {
    private final CourseRepository courseRepository;
    public CourseServiceImp(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses(Integer offset, Integer limit) {
        return courseRepository.getAllCourses(offset, limit);
    }

    @Override
    public Course findCourseById(Integer courseId) {
        Course course = courseRepository.findCourseById(courseId);

        if (course == null) {
            throw new GlobalNotFoundException("Course with ID " + courseId + " not found!");
        }

        return course;
    }

    @Override
    public Course insertCourse(CourseRequest courseRequest) {
        return courseRepository.insertCourse(courseRequest);
    }

    @Override
    public Course updateCourse(Integer courseId, CourseRequest courseRequest) {
        Course course = courseRepository.updateCourseById(courseId, courseRequest);
        if (course == null) {
            throw new GlobalNotFoundException("Course with ID " + courseId + " not found!");
        }

        return course;
    }

    @Override
    public Course deleteCourseById(Integer courseId) {
        Course course = courseRepository.deleteCourseById(courseId);
        if (course == null) {
            throw new GlobalNotFoundException("Course with ID " + courseId + " not found!");
        }

        return course;
    }
}
