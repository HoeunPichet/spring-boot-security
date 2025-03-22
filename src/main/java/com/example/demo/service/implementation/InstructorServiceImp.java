package com.example.demo.service.implementation;

import com.example.demo.exception.GlobalNotFoundException;
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
    public Instructor findInstructorById(Integer instructorId) {
        Instructor instructor = instructorRepository.findInstructorById(instructorId);

        if (instructor == null) {
            throw new GlobalNotFoundException("Instructor with ID " + instructorId + " not found!");
        }

        return instructor;
    }

    @Override
    public Instructor insertInstructor(InstructorRequest instructorRequest) {
        return instructorRepository.insertInstructor(instructorRequest);
    }

    @Override
    public Instructor updateInstructor(Integer instructorId, InstructorRequest instructorRequest) {
        Instructor instructor = instructorRepository.updateInstructor(instructorId, instructorRequest);

        if (instructor == null) {
            throw new GlobalNotFoundException("Instructor with ID " + instructorId + " not found!");
        }

        return instructor;
    }

    @Override
    public Instructor deleteInstructorById(Integer instructorId) {
        Instructor instructor = instructorRepository.deleteInstructorById(instructorId);

        if (instructor == null) {
            throw new GlobalNotFoundException("Instructor with ID " + instructorId + " not found!");
        }

        return instructor;
    }
}
