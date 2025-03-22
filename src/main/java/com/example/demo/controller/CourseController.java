package com.example.demo.controller;

import com.example.demo.model.dto.request.CourseRequest;
import com.example.demo.model.dto.response.ApiResponse;
import com.example.demo.model.entity.Course;
import com.example.demo.service.CourseService;
import jakarta.validation.Valid;
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

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> insertCourse(@RequestBody @Valid CourseRequest courseRequest) {
        Course course = courseService.insertCourse(courseRequest);

        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .message("Course has been added successfully!")
                .payload(course)
                .status(HttpStatus.CREATED)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Course>> updateCourseById(@PathVariable("course-id") Integer courseId, @RequestBody @Valid CourseRequest courseRequest) {
        Course course = courseService.updateCourse(courseId, courseRequest);

        if (course != null) {
            ApiResponse<Course> response = ApiResponse.<Course>builder()
                    .message("Course has been updated successfully!")
                    .payload(course)
                    .status(HttpStatus.CREATED)
                    .build();
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .message("Course with ID " + courseId + " not found!")
                .payload(null)
                .status(HttpStatus.NOT_FOUND)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Course>> deleteCourseById(@PathVariable("course-id") Integer courseId) {
        Course course = courseService.deleteCourseById(courseId);

        if (course != null) {
            ApiResponse<Course> response = ApiResponse.<Course>builder()
                    .message("Course ID " + courseId + " has been deleted successfully!")
                    .payload(course)
                    .status(HttpStatus.OK)
                    .build();
            return ResponseEntity.ok(response);
        }

        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .message("Course with ID " + courseId + " not found!")
                .payload(null)
                .status(HttpStatus.NOT_FOUND)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
