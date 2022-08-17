package com.example.StudentApi.service;


import com.example.StudentApi.model.dto.Student;
import com.example.StudentApi.model.resource.StudentRequestResource;

import java.util.List;


public interface IStudentService {

  List<Student> getStudents ();


  Student getStudentById (Long id);


  Long createStudent (Student student);


  void updateStudent (Long id, StudentRequestResource requestResource);


  void partialUpdateStudent (Long id, StudentRequestResource requestResource);


  void deleteStudent (Long id);


  void deleteAllStudents ();
}
