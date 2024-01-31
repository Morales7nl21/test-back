package com.test.technique.dto;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 40, message = "Name should be between 3 and 40 characters")
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 30, message = "Father's Lastname should be between 3 and 30 characters")
    private String fathersLastname;

    @Size(max = 30, message = "Mother's Lastname should be less than or equal to 30 characters")
    private String mothersLastname;

    @Pattern(regexp = "[0-9]{8,15}", message = "Invalid phone number format")
    @Size(min = 8, max = 15, message = "Phone number should be between 8 and 15 characters")
    private String phoneNumber;

    @Pattern(regexp = "[0-9A-Za-z]{2,5}", message = "Invalid prefix number format")
    @Size(min = 2, max = 5, message = "Prefix number should be between 2 and 5 characters")
    private String prefixNumber;

    @NotBlank
    @Size(min = 4, max = 255, message = "Email should be between 4 and 256 characters")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull
    @Past(message = "Birthdate should be in the past")
    private Date birthdate;
}
