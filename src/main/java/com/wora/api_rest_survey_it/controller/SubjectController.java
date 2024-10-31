package com.wora.api_rest_survey_it.controller;


import com.wora.api_rest_survey_it.DTO.Subject.SubjectCreateDTO;
import com.wora.api_rest_survey_it.entity.Subject;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @PostMapping
    public SubjectCreateDTO createSubject(@Valid @RequestBody SubjectCreateDTO subjectCreateDTO){
        return subjectCreateDTO;
    }

}
