package com.example.demo.controller;

import com.example.demo.model.dto.request.StudentRequest;
import com.example.demo.model.dto.response.ApiResponse;
import com.example.demo.model.entity.Student;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
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

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> insertStudent(@RequestBody @Valid StudentRequest studentRequest) {
        Student student = studentService.insertStudent(studentRequest);

        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .message("Student has been added successfully!")
                .payload(student)
                .status(HttpStatus.CREATED)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> insertStudent(@PathVariable("student-id") Integer studentId, @RequestBody @Valid StudentRequest studentRequest) {
        Student student = studentService.updateStudent(studentId, studentRequest);

        if (student != null) {
            ApiResponse<Student> response = ApiResponse.<Student>builder()
                    .message("Student has been updated successfully!")
                    .payload(student)
                    .status(HttpStatus.OK)
                    .build();
            return ResponseEntity.ok(response);
        }

        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .message("Student with ID " + studentId + " not found!")
                .payload(null)
                .status(HttpStatus.NOT_FOUND)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> deleteStudentById(@PathVariable("student-id") Integer studentId) {
        Student student = studentService.deleteStudentById(studentId);

        if (student != null) {
            ApiResponse<Student> response = ApiResponse.<Student>builder()
                    .message("Student ID " + studentId + " has been deleted successfully!")
                    .payload(student)
                    .status(HttpStatus.OK)
                    .build();
            return ResponseEntity.ok(response);
        }

        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .message("Student with ID " + studentId + " not found!")
                .payload(null)
                .status(HttpStatus.NOT_FOUND)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
