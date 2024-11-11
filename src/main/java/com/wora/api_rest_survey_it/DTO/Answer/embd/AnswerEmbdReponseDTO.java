package com.wora.api_rest_survey_it.DTO.Answer.embd;

import com.wora.api_rest_survey_it.DTO.Question.embd.QuestionEmbdResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerEmbdReponseDTO {

    private Long id;
    private String text;
    private Integer selectionCount;

}
