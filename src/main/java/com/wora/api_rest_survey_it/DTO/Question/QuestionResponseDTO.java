package com.wora.api_rest_survey_it.DTO.Question;

import com.wora.api_rest_survey_it.DTO.Answer.embd.AnswerEmbdReponseDTO;
import com.wora.api_rest_survey_it.DTO.Subject.embd.SubjectEmbdResponseDTO;
import com.wora.api_rest_survey_it.entity.Enum.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponseDTO {

    private Long id;
    private QuestionType questionType;
    private String text;
    private Integer answerCount;
    private SubjectEmbdResponseDTO subject;
    private List<AnswerEmbdReponseDTO> answerList;

}
