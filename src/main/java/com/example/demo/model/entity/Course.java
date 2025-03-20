package com.example.demo.model.entity;

import lombok.Data;

@Data
public class Course {
    private Integer courseId;
    private String courseName;
    private String description;
    private Instructor instructor;
}
