package com.wora.api_rest_survey_it.controller;


import com.wora.api_rest_survey_it.DTO.Answer.AnswerCreateDTO;
import com.wora.api_rest_survey_it.DTO.Answer.AnswerResponseDTO;
import com.wora.api_rest_survey_it.annotation.EXIST.Exist;
import com.wora.api_rest_survey_it.entity.Answer;
import com.wora.api_rest_survey_it.repository.AnswerRepository;
import com.wora.api_rest_survey_it.service.AnswerService;
import jakarta.servlet.ServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping
    public AnswerResponseDTO createAnswer(@Valid  @RequestBody AnswerCreateDTO answerCreateDTO){
        return answerService.createAnswer(answerCreateDTO);
    }

    @GetMapping
    public List<AnswerResponseDTO> getAllAnswers(){
        return answerService.getAllAnswers();
    }

    @GetMapping("/{answerId}")
    public AnswerResponseDTO getAnswer(
            @PathVariable("answerId")
            @Exist(entity = Answer.class, repository = AnswerRepository.class) Long id){
        return answerService.getAnswerById(id);
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity<String> deleteAnswer(
            @PathVariable("answerId")
            @Exist(entity = Answer.class, repository = AnswerRepository.class) Long id, ServletRequest servletRequest){
        if(answerService.deleteById(id)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Answer Deleted succefully");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something happend !!");
    }

}
