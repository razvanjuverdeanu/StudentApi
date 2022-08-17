package com.example.StudentApi.repository;


import com.example.StudentApi.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

  Optional<StudentEntity> findStudentByEmail (String mail);

}
