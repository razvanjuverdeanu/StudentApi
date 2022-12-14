package com.example.StudentApi.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidator.class)
public @interface ValidateDate {

  String[] value ();


  String message () default "{ValidateDate.message";


  Class<?>[] groups () default {};


  Class<? extends Payload>[] payload () default {};
}
