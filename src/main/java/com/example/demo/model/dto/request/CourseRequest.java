package com.example.demo.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CourseRequest {
    @NotBlank(message = "Course Name cannot be blank")
    private String courseName;

    private String description;

    @NotBlank(message = "Instructor ID cannot be blank")
    @Pattern(regexp = "^\\d+$", message = "Instructor ID can be only number")
    private Integer instructorId;
}
