package com.springapp.mvc.validator;

import com.springapp.mvc.model.Group;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Zack on 3/28/15.
 */
@Component
public class GroupValidator implements Validator {
    public boolean supports(Class<?> paramClass){
        return Group.class.equals(paramClass);
    }

    public void validate(Object o, Errors e){
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "groupName", "valid.group");
    }
}
