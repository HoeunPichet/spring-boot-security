package com.example.demo.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class StudentRequest {
    @NotBlank(message = "Student Name cannot be blank")
    private String studentName;

    @Schema(example = "example@gmail.com")
    @Email(message = "Email is not valid")
    private String email;

    private String phoneNumber;

//    @Pattern(regexp = "^\\d+$", message = "Course ID can be only number")
    private List<Integer> coursesId;
}
