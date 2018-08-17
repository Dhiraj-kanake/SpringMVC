package com.jda.user.validator;

import com.jda.user.model.User;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
 
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }
 
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required.email", "email is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required.username", "username is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password", "password is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required.email", "email is empty");
   
    }
}

