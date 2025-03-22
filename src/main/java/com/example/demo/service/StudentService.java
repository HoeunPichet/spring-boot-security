package com.example.demo.service;

import com.example.demo.model.dto.request.StudentRequest;
import com.example.demo.model.entity.Student;
import jakarta.validation.Valid;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents(Integer offset, Integer limit);
    Student findStudentById(Integer id);
    Student insertStudent(@Valid StudentRequest studentRequest);
    Student updateStudent(@Valid Integer studentId, StudentRequest studentRequest);
    Student deleteStudentById(Integer studentId);
}
