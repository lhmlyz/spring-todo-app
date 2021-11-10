package com.app.todo.validator;

import com.app.todo.annotations.UniqueTask;
import com.app.todo.repository.jdbc.TodoRepository;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueTaskValidator implements ConstraintValidator<UniqueTask, String> {

    private TodoRepository repository;

    public UniqueTaskValidator(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("Task name validator worked");
        return !repository.isTaskNameExist(s);
    }
}
