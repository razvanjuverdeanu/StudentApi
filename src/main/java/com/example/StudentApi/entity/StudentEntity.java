package com.example.StudentApi.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "students")
public class StudentEntity {

    @Id
    @SequenceGenerator(name = "student_sequence",
            allocationSize = 1)
    @GeneratedValue(generator = "student_sequence",
            strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)

    private Long id;

    private String name;

    private String surname;

    private Integer age;

    private String city;

    private String profile;

    private String email;

    private LocalDate dateOfBirth;

    public static Integer calculateAge(LocalDate date) {

        return Period.between(date, LocalDate.now()).getYears();
    }

}
