package com.wora.api_rest_survey_it.controller;


import com.wora.api_rest_survey_it.DTO.Subject.SubjectCreateDTO;
import com.wora.api_rest_survey_it.DTO.Subject.SubjectResponseDTO;
import com.wora.api_rest_survey_it.DTO.Subject.embd.MainSubjectResponse;
import com.wora.api_rest_survey_it.annotation.EXIST.Exist;
import com.wora.api_rest_survey_it.entity.Subject;
import com.wora.api_rest_survey_it.repository.SubjectRepository;
import com.wora.api_rest_survey_it.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping
    public SubjectResponseDTO createSubject(@Valid @RequestBody SubjectCreateDTO subjectCreateDTO){
        return subjectService.createSubject(subjectCreateDTO);
    }

    @GetMapping
    public List<SubjectResponseDTO> getAllSubjects(){
        return subjectService.findAllSubjects();
    }

    @GetMapping("/mainsubject")
    public List<MainSubjectResponse> getAllMainSubjects() {
        return subjectService.findAllMainSubjects();
    }

    @GetMapping("/{subjectId}")
    public Object getSubjectById(@PathVariable("subjectId") @Exist(entity = Subject.class, repository = SubjectRepository.class) Long id){
        return subjectService.findSubjectById(id);
    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<?> deleteSubjectById(@PathVariable("subjectId")
                                               @Exist(entity = Subject.class, repository = SubjectRepository.class)    Long id){
        if(subjectService.deleteById(id)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Subject Deleted Succefully");
        }

        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/{subjectId}")
    public Object updateSubject(
            @PathVariable("subjectId")
            @Exist(entity = Subject.class, repository = SubjectRepository.class) Long id,
            @RequestBody SubjectCreateDTO subjectCreateDTO){
        return subjectService.updateSubject(id , subjectCreateDTO);
    }

}
