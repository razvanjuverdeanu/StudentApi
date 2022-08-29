package com.example.StudentApi.service;


import com.example.StudentApi.entity.StudentEntity;
import com.example.StudentApi.mapper.StudentMapper;
import com.example.StudentApi.model.dto.Student;
import com.example.StudentApi.model.resource.StudentRequestResource;
import com.example.StudentApi.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.StudentApi.entity.StudentEntity.calculateAge;
import static com.example.StudentApi.utils.Constants.*;


@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;


    public List<Student> getStudents() {

        List<Student> students = new ArrayList<>();

        List<StudentEntity> entities = studentRepository.findAll();
        entities.forEach(studentEntity -> students.add(studentMapper.mapFromEntity(studentEntity)));

        return students;
    }


    public Student getStudentById(Long id) {

        return studentMapper.mapFromEntity(getStudentEntity(id));
    }


    public Long createStudent(Student student) {

        checkMailExistance(student.getEmail());

        student.setAge(calculateAge(student.getDateOfBirth()));

        return studentRepository.save(studentMapper.mapToEntity(student)).getId();

    }


    @Transactional
    public void updateStudent(Long id, StudentRequestResource requestResource) {

        StudentEntity studentEntityToBeUpdated = studentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(STUDENT_ID_NOT_FOUND, id)));

        if (requestResource.getEmail() != null) {
            checkMailExistance(requestResource.getEmail());
        }

        studentEntityToBeUpdated.setName(requestResource.getName());
        studentEntityToBeUpdated.setSurname(requestResource.getSurname());
        studentEntityToBeUpdated.setAge(calculateAge(requestResource.getDateOfBirth()));
        studentEntityToBeUpdated.setCity(requestResource.getCity());
        studentEntityToBeUpdated.setEmail(requestResource.getEmail());
        studentEntityToBeUpdated.setProfile(requestResource.getProfile());
        studentEntityToBeUpdated.setDateOfBirth(requestResource.getDateOfBirth());

    }


    @Transactional
    public void partialUpdateStudent(Long id, StudentRequestResource requestResource) {

        StudentEntity studentEntityToBePartialUpdate = studentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(STUDENT_ID_NOT_FOUND, id)));

        if (requestResource.getEmail() != null) {
            checkMailExistance(requestResource.getEmail());
        }

        studentEntityToBePartialUpdate.setName(requestResource.getName() == null ?
                studentEntityToBePartialUpdate.getName() : requestResource.getName());

        studentEntityToBePartialUpdate.setSurname(requestResource.getSurname() == null ?
                studentEntityToBePartialUpdate.getSurname() : requestResource.getSurname());

        studentEntityToBePartialUpdate.setCity(requestResource.getCity() == null ?
                studentEntityToBePartialUpdate.getCity() : requestResource.getCity());

        studentEntityToBePartialUpdate.setProfile(requestResource.getProfile() == null ?
                studentEntityToBePartialUpdate.getProfile() : requestResource.getProfile());

        studentEntityToBePartialUpdate.setEmail(requestResource.getEmail() == null ?
                studentEntityToBePartialUpdate.getEmail() : requestResource.getEmail());

        studentEntityToBePartialUpdate.setDateOfBirth(requestResource.getDateOfBirth() == null ?
                studentEntityToBePartialUpdate.getDateOfBirth() : requestResource.getDateOfBirth());

        studentEntityToBePartialUpdate.setAge(requestResource.getDateOfBirth() == null ?
                studentEntityToBePartialUpdate.getAge() : calculateAge(requestResource.getDateOfBirth()));

    }


    public void deleteStudent(Long id) {

        Optional<StudentEntity> studentToBeDeleted = studentRepository.findById(id);
        if (studentToBeDeleted.isPresent()) {
            studentRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(STUDENT_CANNOT_BE_DELETED, id));
        }
    }


    public void deleteAllStudents() {

        List<Student> students = getStudents();
        if (!students.isEmpty()) {
            studentRepository.deleteAll();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MISSING_STUDENTS);
        }
    }


    private StudentEntity getStudentEntity(Long id) {
        Optional<StudentEntity> responseResource = studentRepository.findById(id);
        if (!responseResource.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(STUDENT_ID_NOT_FOUND, id));
        }
        return studentRepository.findById(id).get();

    }


    private void checkMailExistance(String mail) {

        Optional<StudentEntity> responseResource = studentRepository.findStudentByEmail(mail);
        if (responseResource.isPresent()) {
            throw new IllegalStateException(String.format(EMAIL_EXISTS, mail));
        }
    }
}
