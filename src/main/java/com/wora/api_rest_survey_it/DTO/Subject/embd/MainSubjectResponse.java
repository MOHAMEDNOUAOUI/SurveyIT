package com.wora.api_rest_survey_it.DTO.Subject.embd;

import com.wora.api_rest_survey_it.DTO.SurveyEdition.embd.SurveyEditionForSurvey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainSubjectResponse {

    private Long id;
    private String title;
    private SurveyEditionForSurvey surveyEdition;
    private List<SubjectEmbdResponseDTO> subSubjects;

}
