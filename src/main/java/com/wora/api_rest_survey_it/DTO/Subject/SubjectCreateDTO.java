package com.wora.api_rest_survey_it.DTO.Subject;

import com.wora.api_rest_survey_it.annotation.EXIST.Exist;
import com.wora.api_rest_survey_it.annotation.UNIQUE.Unique;
import com.wora.api_rest_survey_it.entity.Subject;
import com.wora.api_rest_survey_it.entity.SurveyEdition;
import com.wora.api_rest_survey_it.repository.SubjectRepository;
import com.wora.api_rest_survey_it.repository.SurveyEditionRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectCreateDTO {

    @NotBlank
    @Unique(entity = Subject.class, repository = SubjectRepository.class, column = "Title")
    private String title;

    private Long parentId;

    @Exist(entity = SurveyEdition.class, repository = SurveyEditionRepository.class)
    private Long surveyEditionId;

}
