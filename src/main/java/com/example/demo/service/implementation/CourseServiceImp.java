package com.example.demo.service.implementation;

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
}
