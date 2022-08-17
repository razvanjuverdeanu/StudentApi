package com.example.StudentApi.model.resource;


import lombok.Data;


@Data
public class StudentResponseResource extends StudentRequestResource {

  private Long id;

  private Integer age;
}
