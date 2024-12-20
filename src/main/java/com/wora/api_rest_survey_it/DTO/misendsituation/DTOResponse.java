package com.wora.api_rest_survey_it.DTO.misendsituation;

import com.wora.api_rest_survey_it.DTO.Answer.AnswerResponseDTO;
import com.wora.api_rest_survey_it.DTO.Question.QuestionResponseDTO;
import com.wora.api_rest_survey_it.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOResponse {
    private QuestionResponseDTO questionResponseDTO;
    private List<AnswerResponseDTO> answerResponseDTO;
}
