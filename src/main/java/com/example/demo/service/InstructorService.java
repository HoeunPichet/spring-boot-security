package com.example.demo.service;

import com.example.demo.model.entity.Instructor;

import java.util.List;

public interface InstructorService {
    List<Instructor> getAllInstructors(Integer offset, Integer limit);
    Instructor findInstructorById(Integer id);
    Instructor deleteInstructorById(Integer id);
}
