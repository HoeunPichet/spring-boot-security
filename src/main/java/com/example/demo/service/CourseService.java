package com.example.demo.service;

import com.example.demo.model.dto.request.CourseRequest;
import com.example.demo.model.entity.Course;
import jakarta.validation.Valid;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses(Integer offset, Integer limit);
    Course findCourseById(Integer id);
    Course deleteCourseById(Integer courseId);
    Course insertCourse(@Valid CourseRequest courseRequest);
    Course updateCourse(Integer courseId, @Valid CourseRequest courseRequest);
}
