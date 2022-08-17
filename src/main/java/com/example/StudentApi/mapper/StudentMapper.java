package com.example.StudentApi.mapper;


import com.example.StudentApi.entity.StudentEntity;
import com.example.StudentApi.model.dto.Student;
import com.example.StudentApi.model.resource.StudentRequestResource;
import com.example.StudentApi.model.resource.StudentResponseResource;
import org.springframework.stereotype.Component;


@Component
public class StudentMapper {

    public Student mapFromDto(StudentRequestResource resource) {

        if (resource == null) {
            return null;
        }
        final Student destination = new Student();
        destination.setName(resource.getName());
        destination.setSurname(resource.getSurname());
        destination.setCity(resource.getCity());
        destination.setProfile(resource.getProfile());
        destination.setEmail(resource.getEmail());
        destination.setDateOfBirth(resource.getDateOfBirth());
        return destination;
    }


    public StudentResponseResource mapToDto(Student source) {

        if (source == null) {
            return null;
        }
        StudentResponseResource destination = new StudentResponseResource();

        destination.setId(source.getId());
        destination.setName(source.getName());
        destination.setSurname(source.getSurname());
        destination.setCity(source.getCity());
        destination.setProfile(source.getProfile());
        destination.setEmail(source.getEmail());
        destination.setDateOfBirth(source.getDateOfBirth());
        destination.setAge(source.getAge());

        return destination;

    }


    public StudentEntity mapToEntity(Student source) {

        if (source == null) {
            return null;
        }
        final StudentEntity destination = new StudentEntity();
        destination.setName(source.getName());
        destination.setSurname(source.getSurname());
        destination.setCity(source.getCity());
        destination.setEmail(source.getEmail());
        destination.setProfile(source.getProfile());
        destination.setDateOfBirth(source.getDateOfBirth());
        destination.setAge(source.getAge());

        return destination;
    }


    public Student mapFromEntity(StudentEntity source) {

        if (source == null) {
            return null;
        }
        Student destination = new Student();

        destination.setId(source.getId());
        destination.setName(source.getName());
        destination.setSurname(source.getSurname());
        destination.setCity(source.getCity());
        destination.setProfile(source.getProfile());
        destination.setDateOfBirth(source.getDateOfBirth());
        destination.setEmail(source.getEmail());
        destination.setAge(source.getAge());

        return destination;
    }
}
