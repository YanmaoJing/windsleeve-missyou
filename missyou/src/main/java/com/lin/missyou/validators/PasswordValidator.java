package com.lin.missyou.validators;

import com.lin.missyou.dto.PersonDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordEqual, PersonDTO> {
    @Override
    public void initialize(PasswordEqual constraintAnnotation) {

    }

    @Override
    public boolean isValid(PersonDTO personDTO, ConstraintValidatorContext constraintValidatorContext) {
        String  password1 = personDTO.getPassword1();
        String  password2 = personDTO.getPassword2();
        boolean math = password1.equals(password2);
        return math;
    }
}
