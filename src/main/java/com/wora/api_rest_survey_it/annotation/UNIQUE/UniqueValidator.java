package com.wora.api_rest_survey_it.annotation.UNIQUE;

import com.wora.api_rest_survey_it.entity.Owner;
import com.wora.api_rest_survey_it.repository.OwnerRepository;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

@Component
public class UniqueValidator implements ConstraintValidator<Unique, String> {

    private final ApplicationContext applicationContext;
    private Class<?> repository;
    private String message;
    private String fieldName;

    public UniqueValidator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void initialize(Unique unique) {
        this.repository = unique.repository();
        this.message = unique.message();
        this.fieldName = unique.column();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (name == null || name.trim().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Name cannot be empty")
                    .addConstraintViolation();
            return false;
        }

        try {
            Object repositoryBean = applicationContext.getBean(repository);

            Method findByNameMethod = repositoryBean.getClass().getMethod("findBy"+fieldName, String.class);

            if (findByNameMethod == null) {
                throw new IllegalStateException("Repository must have a method findByName or similar");
            }

            Object result = findByNameMethod.invoke(repositoryBean, name);
            boolean exists;

            if (result instanceof Optional<?>) {
                exists = ((Optional<?>) result).isPresent();
            } else {
                exists = result != null;
            }

            if (exists) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                                message.replace("${validatedValue}", name))
                        .addConstraintViolation();
                return false;
            }

            return true;

        } catch (Exception e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            "Error checking uniqueness: " + e.getMessage())
                    .addConstraintViolation();
            return false;
        }
    }

}