package com.wora.api_rest_survey_it.DTO.Answer;

import com.wora.api_rest_survey_it.DTO.Question.embd.QuestionEmbdResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerResponseDTO {

    private Long id;
    private String text;
    private Integer selectionCount;
    private QuestionEmbdResponseDTO question;
}
