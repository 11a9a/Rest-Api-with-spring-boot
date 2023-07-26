package com.example.secandlog.dto;
//
//import jakarta.validation.constraints.Max;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import javax.validation.constraints.*;

@Data
public class CarModelDTO {

    private Long Id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Engine cannot be blank")
    private String engine;

    @NotBlank(message = "Color cannot be blank")
    private String color;

    @Min(value = 1886, message = "Year must be greater than 1886")
    @Max(value = 2023, message = "Year must be less than 2023")
    private int createdYear;
}
