package com.app.todo.validator;

import com.app.todo.model.Todo;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

//@Component
public class TodoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Todo.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
//        Todo todoForm = (Todo) target;
//
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "task", "todo.taskName.required");
//        if (!GenericValidator.isInRange(todoForm.getTask().length(), 3, 50)) {
//            errors.rejectValue("task", "todo.taskName.length");
//        }
//
//        if(!todoForm.getTask().matches("^[a-zA-Z]+$")){
//            errors.rejectValue("task", "todo.taskName.isAlpha");
//        }
//
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "context", "todo.context.required");
//        if(!GenericValidator.isInRange(todoForm.getContext().length(), 1, 100)){
//            errors.rejectValue("context", "todo.context.length");
//        }
//        ValidationUtils.rejectIfEmpty(errors, "deadline", "todo.deadline.required");


    }


}
