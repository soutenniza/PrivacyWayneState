package com.springapp.mvc.validator;

import com.springapp.mvc.model.Attribute;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Zack on 3/28/15.
 */
@Component
public class AttributeValidator implements Validator {
    public boolean supports(Class<?> paramClass){
        return Attribute.class.equals(paramClass);
    }

    public void validate(Object o, Errors e){
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "attributeLabel", "valid.attribute");
    }

}