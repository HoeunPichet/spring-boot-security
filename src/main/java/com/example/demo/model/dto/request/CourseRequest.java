package com.example.demo.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseRequest {
    @NotBlank(message = "Course Name cannot be blank")
    private String courseName;

    private String description;

    @Min(value = 1, message = "Instructor ID must be greater than 0")
    private Integer instructorId;
}
