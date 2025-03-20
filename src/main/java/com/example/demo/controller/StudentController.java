package com.example.demo.controller;

import com.example.demo.model.dto.response.ApiResponse;
import com.example.demo.model.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController (StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    ResponseEntity<ApiResponse<List<Student>>> getAllStudents(@RequestParam(required = false, defaultValue = "1") Integer offset, @RequestParam(required = false, defaultValue = "10") Integer limit) {
        List<Student> students = studentService.getAllStudents(offset, limit);

        ApiResponse<List<Student>> response = ApiResponse.<List<Student>>builder()
                .message("Get all students successfully!")
                .payload(students)
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> findStudentById(@PathVariable("student-id") Integer studentId) {
        Student student = studentService.findStudentById(studentId);

        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .message("Student has been found successfully!")
                .payload(student)
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.ok(response);
    }
}
