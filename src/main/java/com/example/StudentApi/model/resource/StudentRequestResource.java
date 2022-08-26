package com.example.StudentApi.model.resource;


import com.example.StudentApi.validation.ValidatDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

import static com.example.StudentApi.utils.Constants.*;


@Data
@ValidatDate(value = {"dateOfBirth"}, message = AGE_BETWEEN_18_AND_100)
public class StudentRequestResource {

    @NotNull(message = NOT_NULL)
    @Pattern(regexp = "^[a-zA-Z]+$", message = ONLY_LETTERS)
    @Size(min = 1, max = 30, message = BETWEEN_1_AND_30)
    private String name;

    @NotNull(message = NOT_NULL)
    @Pattern(regexp = "^[a-zA-Z]+$", message = ONLY_LETTERS)
    @Size(min = 1, max = 30, message = BETWEEN_1_AND_30)
    private String surname;

    @NotNull(message = NOT_NULL)
    @Pattern(regexp = "^[a-zA-Z]+$", message = ONLY_LETTERS)
    @Size(min = 1, max = 30, message = BETWEEN_1_AND_30)
    private String city;

    @NotNull(message = NOT_NULL)
    @Pattern(regexp = "^[a-zA-Z]+$", message = ONLY_LETTERS)
    @Size(min = 1, max = 30, message = BETWEEN_1_AND_30)
    private String profile;

    @NotNull(message = NOT_NULL)
    @NotEmpty(message = "%s should not be empty")
    @Size(max = 100, message = "%s should not be higher than 100 characters")
    @Email
    private String email;

    @NotNull(message = NOT_NULL)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
}
