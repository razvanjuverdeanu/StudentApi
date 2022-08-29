package com.example.StudentApi.validation;


import com.example.StudentApi.model.resource.StudentRequestResource;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateValidator implements ConstraintValidator<ValidateDate, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        if (value instanceof StudentRequestResource) {
            StudentRequestResource requestResource = (StudentRequestResource) value;

            return validateDate(requestResource.getDateOfBirth());
        }
        return true;
    }


    private boolean validateDate(LocalDate date) {

        if (date != null) {
            if (date.getYear() < LocalDate.now().getYear() - 100 || date.getYear() > LocalDate.now().getYear() - 18) {
                return false;
            }
        }

        return true;
    }
}
