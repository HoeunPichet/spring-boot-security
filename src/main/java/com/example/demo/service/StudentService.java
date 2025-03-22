package com.example.demo.service;

import com.example.demo.model.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents(Integer offset, Integer limit);
    Student findStudentById(Integer id);
    Student deleteStudentById(Integer studentId);
}
