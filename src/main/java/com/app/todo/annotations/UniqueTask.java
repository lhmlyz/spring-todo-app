package com.app.todo.annotations;

import com.app.todo.validator.UniqueTaskValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Constraint(validatedBy = UniqueTaskValidator.class)
@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UniqueTask {
    String message() default "Task is already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
