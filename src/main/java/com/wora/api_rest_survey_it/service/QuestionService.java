package com.wora.api_rest_survey_it.service;


import com.wora.api_rest_survey_it.DTO.Question.QuestionCreateDTO;
import com.wora.api_rest_survey_it.DTO.Question.QuestionResponseDTO;
import com.wora.api_rest_survey_it.DTO.misendsituation.DTOResponse;
import com.wora.api_rest_survey_it.DTO.misendsituation.DTOcreateQuestionWithAnswers;
import org.springframework.stereotype.Service;

import java.util.List;

public interface QuestionService {

    QuestionResponseDTO createQuestion(QuestionCreateDTO questionCreateDTO);
    List<QuestionResponseDTO> findAllQuestions();
    QuestionResponseDTO findQuestionById(Long id);
    boolean deleteQuestion(Long id);
    QuestionResponseDTO updateQuestion(Long id , QuestionCreateDTO questionCreateDTO);
    DTOResponse createWithAnswers(DTOcreateQuestionWithAnswers dtOcreateQuestionWithAnswers);
}
