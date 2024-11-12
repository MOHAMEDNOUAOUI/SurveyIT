package com.wora.api_rest_survey_it.annotation.EXIST;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class ExistValidator implements ConstraintValidator<Exist,Long> {

    @Autowired
    private ApplicationContext contextProvider;


    private Class<?> entity;
    private Class<?> repository;

    @Override
    public void initialize(Exist exist) {
       entity = exist.entity();
       repository = exist.repository();
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {

        if(aLong == null){
            return false;
        }


        try{
            JpaRepository<?, Long> repositoryBean = (JpaRepository<?, Long>) contextProvider.getBean(repository);
            Optional<?> object = repositoryBean.findById(aLong);
            return object.isPresent();
        }
        catch (ClassCastException e) {
            e.printStackTrace();
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


}
