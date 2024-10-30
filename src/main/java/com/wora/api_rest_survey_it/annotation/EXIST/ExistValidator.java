package com.wora.api_rest_survey_it.annotation.EXIST;

import com.wora.api_rest_survey_it.config.ApplicationContextProvider;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;


@Component
public class ExistValidator implements ConstraintValidator<Exist,Long> {

    @Autowired
    private ApplicationContext contextProvider;

    private Class<?> entity;
    private Class<?> repostiroy;

    @Override
    public void initialize(Exist constraintAnnotation) {
       entity = constraintAnnotation.entity();
       repostiroy = constraintAnnotation.repository();
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {

        if(aLong == null){
            return false;
        }

        Object repositoryBean = contextProvider.getBean(repostiroy);

        try{
            Object object = repostiroy.getMethod("findById" , Long.class).invoke(repositoryBean , aLong);
            return object == null;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }

    }


}
