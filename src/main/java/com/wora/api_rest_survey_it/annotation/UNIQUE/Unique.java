package com.wora.api_rest_survey_it.annotation.UNIQUE;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE,ElementType.PARAMETER,ElementType.FIELD,ElementType.METHOD})
public @interface Unique {
    String message() default "this name is not unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    Class<?> entity();
    Class<?> repository();
    String column();
}
