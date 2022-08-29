package com.example.StudentApi.model.resource;


import com.example.StudentApi.validation.ValidateDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import static com.example.StudentApi.utils.Constants.*;


@Data
@ValidateDate(value = {"dateOfBirth"}, message = AGE_BETWEEN_18_AND_100)
public class StudentRequestResource {

    @Pattern(regexp = "^[a-zA-Z]+$", message = ONLY_LETTERS)
    @Size(min = 1, max = 30, message = BETWEEN_1_AND_30)
    private String name;

    @Pattern(regexp = "^[a-zA-Z]+$", message = ONLY_LETTERS)
    @Size(min = 1, max = 30, message = BETWEEN_1_AND_30)
    private String surname;

    @Pattern(regexp = "^[a-zA-Z]+$", message = ONLY_LETTERS)
    @Size(min = 1, max = 30, message = BETWEEN_1_AND_30)
    private String city;

    @Pattern(regexp = "^[a-zA-Z]+$", message = ONLY_LETTERS)
    @Size(min = 1, max = 30, message = BETWEEN_1_AND_30)
    private String profile;

    @Size(min = 1, max = 100, message = "%s should not be higher than 100 characters")
    @Email
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
}
