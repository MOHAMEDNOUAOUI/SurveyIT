package com.wora.api_rest_survey_it.DTO.SurveyEdition;

import com.wora.api_rest_survey_it.annotation.EXIST.Exist;
import com.wora.api_rest_survey_it.entity.Survey;
import com.wora.api_rest_survey_it.repository.SurveyRepository;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyEditionCreateDTO {

    @NotNull
    private LocalDate creationDate;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private Integer year;
    @NotNull
    @Exist(entity = Survey.class , repository = SurveyRepository.class)
    private Long surveyId;

}
