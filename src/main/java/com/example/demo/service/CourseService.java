package com.example.demo.service;

import com.example.demo.model.entity.Course;
import java.util.List;

public interface CourseService {
    List<Course> getAllCourses(Integer offset, Integer limit);
}
