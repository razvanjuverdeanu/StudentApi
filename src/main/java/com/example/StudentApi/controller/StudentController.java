package com.example.StudentApi.controller;


import com.example.StudentApi.mapper.StudentMapper;
import com.example.StudentApi.model.resource.StudentRequestResource;
import com.example.StudentApi.model.resource.StudentResponseResource;
import com.example.StudentApi.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/app/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    private final StudentMapper studentMapper;


    @Operation(summary = "Get all students", description = "Get a list of all students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all the students",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentRequestResource.class))}),
            @ApiResponse(responseCode = "404", description = "No students were found",
                    content = @Content)
    })
    @GetMapping(path = "/all")
    public List<StudentResponseResource> getAllStudents() {

        List<StudentResponseResource> responseResources = new ArrayList<>();

        studentService.getStudents().forEach(student -> responseResources.add(studentMapper.mapToDto(student)));

        return responseResources;
    }

    @Operation(summary = "Get student by id", description = "Get student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found student by id ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentRequestResource.class))}),
            @ApiResponse(responseCode = "404", description = "No student was found by id",
                    content = @Content)
    })
    @GetMapping(path = "{id}")
    public ResponseEntity<StudentResponseResource> getStudent(@PathVariable Long id) {

        StudentResponseResource responseResource = studentMapper.mapToDto(studentService.getStudentById(id));
        return new ResponseEntity<>(responseResource, HttpStatus.OK);

    }

    @Operation(summary = "Create student", description = "Create student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentRequestResource.class))}),
            @ApiResponse(responseCode = "400", description = "Broken rule",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<StudentResponseResource> createStudent(
            @RequestBody @Valid StudentRequestResource requestResource
    ) {

        Long id = studentService.createStudent(studentMapper.mapFromDto(requestResource));

        StudentResponseResource responseResource = studentMapper.mapToDto(studentService.getStudentById(id));

        return new ResponseEntity<>(responseResource, HttpStatus.CREATED);
    }


    @Operation(summary = "Update student by id", description = "Update student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student by id was updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentRequestResource.class))}),
            @ApiResponse(responseCode = "400", description = "Broken rule",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No student was found by id",
                    content = @Content)
    })
    @PutMapping(path = "{id}")
    public ResponseEntity<StudentResponseResource> updateStudent(
            @PathVariable Long id,
            @RequestBody @Valid StudentRequestResource requestResource
    ) {

        studentService.updateStudent(id, requestResource);
        StudentResponseResource responseResource = studentMapper.mapToDto(studentService.getStudentById(id));
        return ResponseEntity.ok(responseResource);
    }

    @Operation(summary = "Partial update student by id", description = "Partial update student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student by id was partially updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentRequestResource.class))}),
            @ApiResponse(responseCode = "400", description = "Broken rule",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No student was found by id",
                    content = @Content)
    })
    @PatchMapping(path = "{id}")
    public ResponseEntity<StudentResponseResource> partialUpdateStudent(
            @PathVariable Long id,
            @Validated @RequestBody StudentRequestResource requestResource
    ) {

        studentService.partialUpdateStudent(id, requestResource);
        StudentResponseResource responseResource = studentMapper.mapToDto(studentService.getStudentById(id));
        return ResponseEntity.ok(responseResource);
    }

    @Operation(summary = "Delete student by id", description = "Delete student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student by id was deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentRequestResource.class))}),
            @ApiResponse(responseCode = "404", description = "No student was found by id",
                    content = @Content)
    })
    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable(value = "id") Long id) {

        studentService.deleteStudent(id);
        return new ResponseEntity<>(String.format("Student with id %d was deleted", id), HttpStatus.ACCEPTED);

    }

    @Operation(summary = "Delete all students", description = "Delete all students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All students were removed",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentRequestResource.class))}),
            @ApiResponse(responseCode = "404", description = "No students were found",
                    content = @Content)
    })
    @DeleteMapping(path = "/all")
    public ResponseEntity<String> deleteAllStudents() {

        studentService.deleteAllStudents();
        return new ResponseEntity<>("All students were deleted", HttpStatus.ACCEPTED);

    }


}
