package com.wora.api_rest_survey_it.DTO.Survey;

import com.wora.api_rest_survey_it.DTO.Owner.embd.OwnerEmbdResponse;
import com.wora.api_rest_survey_it.DTO.Owner.embd.OwnerResponseForSurvey;
import com.wora.api_rest_survey_it.DTO.SurveyEdition.embd.SurveyEditionForSurvey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResponseDTO {

    private Long id;
    private String title;
    private String description;
    private OwnerResponseForSurvey owner;
    private Set<SurveyEditionForSurvey> surveyEditionList;

}
