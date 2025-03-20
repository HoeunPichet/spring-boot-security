package com.example.demo.service;

import com.example.demo.model.dto.request.InstructorRequest;
import com.example.demo.model.entity.Instructor;
import jakarta.validation.Valid;

import java.util.List;

public interface InstructorService {
    List<Instructor> getAllInstructors(Integer offset, Integer limit);
    Instructor findInstructorById(Integer id);
    Instructor deleteInstructorById(Integer id);
    Instructor insertInstructor(InstructorRequest instructorRequest);

    Instructor updateInstructor(Integer instructorId, @Valid InstructorRequest instructorRequest);
}
