package com.example.demo.service.implementation;

import com.example.demo.model.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService {
    private final StudentRepository studentRepository;
    public StudentServiceImp(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents(Integer offset, Integer limit) {
        return studentRepository.getAllStudents(offset, limit);
    }

    @Override
    public Student findStudentById(Integer id) {
        return studentRepository.findStudentById(id);
    }
}
