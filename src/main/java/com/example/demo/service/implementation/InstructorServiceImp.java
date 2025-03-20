package com.example.demo.service.implementation;

import com.example.demo.model.dto.request.InstructorRequest;
import com.example.demo.model.entity.Instructor;
import com.example.demo.repository.InstructorRepository;
import com.example.demo.service.InstructorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImp implements InstructorService {
    private final InstructorRepository instructorRepository;
    public InstructorServiceImp(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Instructor> getAllInstructors(Integer offset, Integer limit) {
        return instructorRepository.getAllInstructors(offset, limit);
    }

    @Override
    public Instructor findInstructorById(Integer id) {
        return instructorRepository.findInstructorById(id);
    }

    @Override
    public Instructor insertInstructor(InstructorRequest instructorRequest) {
        return instructorRepository.insertInstructor(instructorRequest);
    }

    @Override
    public Instructor updateInstructor(Integer instructorId, InstructorRequest instructorRequest) {
        return instructorRepository.updateInstructor(instructorId, instructorRequest);
    }

    @Override
    public Instructor deleteInstructorById(Integer id) {
        return instructorRepository.deleteInstructorById(id);
    }
}
