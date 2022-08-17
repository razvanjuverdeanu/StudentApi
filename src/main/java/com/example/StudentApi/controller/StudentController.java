package com.example.StudentApi.controller;


import com.example.StudentApi.mapper.StudentMapper;
import com.example.StudentApi.model.resource.StudentRequestResource;
import com.example.StudentApi.model.resource.StudentResponseResource;
import com.example.StudentApi.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/app/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    private final StudentMapper studentMapper;


    @GetMapping
    public List<StudentResponseResource> getAllStudents() {

        List<StudentResponseResource> responseResources = new ArrayList<>();

        studentService.getStudents().forEach(student -> responseResources.add(studentMapper.mapToDto(student)));

        return responseResources;
    }


    @GetMapping(path = "{id}")
    public StudentResponseResource getStudent(@PathVariable Long id) {

        return studentMapper.mapToDto(studentService.getStudentById(id));

    }


    @PostMapping
    public ResponseEntity<StudentResponseResource> createStudent(
            @RequestBody @Validated StudentRequestResource requestResource
    ) {

        Long id = studentService.createStudent(studentMapper.mapFromDto(requestResource));

        StudentResponseResource responseResource = studentMapper.mapToDto(studentService.getStudentById(id));

        return new ResponseEntity<>(responseResource, HttpStatus.CREATED);
    }


    @PutMapping(path = "{id}")
    public ResponseEntity<StudentResponseResource> updateStudent(
            @PathVariable Long id,
            @RequestBody @Validated StudentRequestResource requestResource
    ) {

        studentService.updateStudent(id, requestResource);
        StudentResponseResource responseResource = studentMapper.mapToDto(studentService.getStudentById(id));
        return ResponseEntity.ok(responseResource);
    }


    @PatchMapping(path = "{id}")
    public ResponseEntity<StudentResponseResource> partialUpdateStudent(
            @PathVariable Long id,
            @RequestBody StudentRequestResource requestResource
    ) {

        studentService.partialUpdateStudent(id, requestResource);
        StudentResponseResource responseResource = studentMapper.mapToDto(studentService.getStudentById(id));
        return ResponseEntity.ok(responseResource);
    }


    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable(value = "id") Long id) {

        studentService.deleteStudent(id);
        return new ResponseEntity<>(String.format("Student with id %d was deleted", id), HttpStatus.ACCEPTED);

    }


    @DeleteMapping()
    public ResponseEntity<String> deleteAllStudents() {

        studentService.deleteAllStudents();
        return new ResponseEntity<>("All students were deleted", HttpStatus.ACCEPTED);

    }


}
