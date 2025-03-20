package com.example.demo.controller;

import com.example.demo.model.dto.response.ApiResponse;
import com.example.demo.model.entity.Course;
import com.example.demo.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses(@RequestParam(required = false, defaultValue = "1") Integer offset, @RequestParam(required = false, defaultValue = "10") Integer limit) {
        List<Course> courses = courseService.getAllCourses(offset, limit);

        ApiResponse<List<Course>> response = ApiResponse.<List<Course>>builder()
                .message("Get all courses successfully!")
                .payload(courses)
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Course>> findCourseById(@PathVariable("course-id") Integer courseId) {
        Course course = courseService.findCourseById(courseId);

        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .message("Course has been found successfully!")
                .payload(course)
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.ok(response);
    }
}
