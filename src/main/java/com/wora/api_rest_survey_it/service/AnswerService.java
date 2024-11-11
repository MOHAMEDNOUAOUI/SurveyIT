package com.wora.api_rest_survey_it.service;

import com.wora.api_rest_survey_it.DTO.Answer.AnswerCreateDTO;
import com.wora.api_rest_survey_it.DTO.Answer.AnswerResponseDTO;

import java.util.List;

public interface AnswerService {
    AnswerResponseDTO createAnswer(AnswerCreateDTO answerCreateDTO);
    List<AnswerResponseDTO> getAllAnswers();
    AnswerResponseDTO getAnswerById(Long id);
    boolean deleteById(Long id);
}
