package com.wora.api_rest_survey_it.annotation.UNIQUE;

import com.wora.api_rest_survey_it.entity.Owner;
import com.wora.api_rest_survey_it.repository.OwnerRepository;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Component
public class UniqueValidator implements ConstraintValidator<Unique , String> {

    @Autowired
    private ApplicationContext applicationContext;

    private String column;
    private Class<?> entityClass;
    private Class<?> repositoryClass;

    @Override
    public void initialize(Unique unique){
        column = unique.column();
        entityClass =unique.entity();
        repositoryClass = unique.repository();
    }


    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        if(s == null || s.isEmpty()){
            return false;
        }

        Object repositoryBean= applicationContext.getBean(repositoryClass);


        try{
            Object result = repositoryClass.getMethod("findByName" , String.class).invoke(repositoryBean , s);
            return result == null;
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
