package com.test.technique.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressDTO {

    @NotBlank(message = "Street is required")
    @Size(max = 255, message = "Street should not exceed 255 characters")
    private String street;

    @Size(max = 10, message = "Interior number should not exceed 10 characters")
    private String interiorNumber;

    @Size(max = 10, message = "Exterior number should not exceed 10 characters")
    private String exteriorNumber;

    @Size(max = 255, message = "Colony should not exceed 255 characters")
    private String colony;

    @Size(max = 255, message = "Municipality should not exceed 255 characters")
    private String municipality;

}