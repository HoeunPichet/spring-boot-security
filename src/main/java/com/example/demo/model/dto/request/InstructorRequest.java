package com.example.demo.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InstructorRequest {
    @NotBlank(message = "Instructor Name cannot be Blank")
    private String instructorName;
    @Email(message = "Email is not valid")
    private String email;
}