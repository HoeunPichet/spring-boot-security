package com.example.demo.service.implementation;

import com.example.demo.model.dto.request.StudentRequest;
import com.example.demo.model.entity.Student;
import com.example.demo.repository.StudentCourseRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentCourseRepository studentCourseRepository;
    public StudentServiceImp(StudentRepository studentRepository, StudentCourseRepository studentCourseRepository){
        this.studentRepository = studentRepository;
        this.studentCourseRepository = studentCourseRepository;
    }

    @Override
    public List<Student> getAllStudents(Integer offset, Integer limit) {
        return studentRepository.getAllStudents(offset, limit);
    }

    @Override
    public Student findStudentById(Integer id) {
        return studentRepository.findStudentById(id);
    }

    @Override
    public Student insertStudent(StudentRequest studentRequest) {
        Integer addedStudentId = studentRepository.insertStudent(studentRequest);

        for (Integer courseId : studentRequest.getCoursesId()) {
            studentCourseRepository.insertStudentCourse(addedStudentId, courseId);
        }
        return this.findStudentById(addedStudentId);
    }

    @Override
    public Student updateStudent(Integer studentId, StudentRequest studentRequest) {
        Integer updatedStudentId = studentRepository.updateStudent(studentId, studentRequest);

        studentCourseRepository.deleteStudentCourse(updatedStudentId);
        for (Integer courseId : studentRequest.getCoursesId()) {
            studentCourseRepository.insertStudentCourse(updatedStudentId, courseId);
        }
        return this.findStudentById(updatedStudentId);
    }


    @Override
    public Student deleteStudentById(Integer studentId) {
        return studentRepository.deleteStudentById(studentId);
    }
}
