package com.example.demo.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InstructorRequest {
    @NotBlank(message = "Instructor Name cannot be blank")
    private String instructorName;

    @Schema(example = "example@gmail.com")
    @Email(message = "Email is not valid")
    private String email;
}