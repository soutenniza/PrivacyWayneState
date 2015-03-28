package com.springapp.mvc.validator;

import com.springapp.mvc.model.Person;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Van on 3/28/2015.
 */
@Component
public class PersonValidator implements Validator {
    public boolean supports(Class<?> paramClass){
        return Person.class.equals(paramClass);
    }

    public void validate(Object o, Errors e){
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "personName", "valid.person");
    }
}
