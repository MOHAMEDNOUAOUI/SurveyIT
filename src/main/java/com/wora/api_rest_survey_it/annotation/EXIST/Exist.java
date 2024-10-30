package com.wora.api_rest_survey_it.annotation.EXIST;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = ExistValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE,ElementType.FIELD,ElementType.TYPE,ElementType.METHOD , ElementType.PARAMETER})
public @interface Exist {
    String message() default "This id doesnt exist";
    Class<? extends Payload>[] payload() default {};
    Class<?>[] groups() default {};

    Class<?> entity();
    Class<?> repository();
}
