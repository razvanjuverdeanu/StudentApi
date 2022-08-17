package com.example.StudentApi.model.dto;


import lombok.Data;

import javax.validation.constraints.Email;
import java.time.LocalDate;


@Data
public class Student {

  private Long id;

  private String name;

  private String surname;

  private String city;

  private Integer age;

  private String profile;

  @Email
  private String email;

  private LocalDate dateOfBirth;
}
