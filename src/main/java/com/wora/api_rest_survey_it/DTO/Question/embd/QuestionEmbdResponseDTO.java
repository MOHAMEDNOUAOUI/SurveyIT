package com.wora.api_rest_survey_it.DTO.Question.embd;

import com.wora.api_rest_survey_it.DTO.Subject.embd.SubjectEmbdResponseDTO;
import com.wora.api_rest_survey_it.entity.Enum.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionEmbdResponseDTO {

    private Long id;
    private QuestionType questionType;
    private String text;
    private Integer answerCount;

}
