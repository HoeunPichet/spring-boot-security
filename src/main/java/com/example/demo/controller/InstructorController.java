package com.example.demo.controller;

import com.example.demo.model.dto.response.ApiResponse;
import com.example.demo.model.entity.Course;
import com.example.demo.model.entity.Instructor;
import com.example.demo.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instructors")
public class InstructorController {
    private final InstructorService instructorService;
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    ResponseEntity<ApiResponse<List<Instructor>>> getAllInstructors(@RequestParam(required = false, defaultValue = "1") Integer offset, @RequestParam(required = false, defaultValue = "10") Integer limit) {
        List<Instructor> instructors = instructorService.getAllInstructors(offset, limit);

        ApiResponse<List<Instructor>> response = ApiResponse.<List<Instructor>>builder()
                .message("Get all instructors successfully!")
                .payload(instructors)
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{instructor-id}")
    ResponseEntity<ApiResponse<Instructor>> findInstructorById(@PathVariable("instructor-id") Integer instructorId) {
        Instructor instructor = instructorService.findInstructorById(instructorId);
        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                .message("Instructor has been found successfully!")
                .payload(instructor)
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.ok(response);
    }
}
