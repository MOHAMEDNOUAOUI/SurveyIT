package com.wora.api_rest_survey_it.DTO.Subject;

import com.wora.api_rest_survey_it.DTO.Question.embd.QuestionEmbdResponseDTO;
import com.wora.api_rest_survey_it.DTO.Subject.embd.SubjectParentResponseDTO;
import com.wora.api_rest_survey_it.DTO.Survey.embd.SurveyEmbdResponse;
import com.wora.api_rest_survey_it.DTO.SurveyEdition.SurveyEditionResponseDTO;
import com.wora.api_rest_survey_it.DTO.SurveyEdition.embd.SurveyEditionForSurvey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResponseDTO {

    private Long id;
    private String title;
    private SurveyEditionForSurvey surveyEdition;
    private SubjectParentResponseDTO parent;
    private List<QuestionEmbdResponseDTO> questionList;

}
