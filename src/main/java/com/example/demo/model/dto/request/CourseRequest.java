package com.example.demo.model.dto.request;

import lombok.Data;

@Data
public class CourseRequest {
    private String courseName;
    private String description;
    private Integer instructorId;
}
