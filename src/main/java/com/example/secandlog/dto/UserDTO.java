package com.example.secandlog.dto;

//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
import javax.validation.constraints.*;
import lombok.Data;

@Data
public class UserDTO {
    @NotBlank(message = "Firstname cannot be blank")
    private String firstName;

    @NotBlank(message = "Lastname cannot be blank")
    private String lastName;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Minimum 8 characters long")
    private String password;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email cannot be blank")
    private String email;
}
