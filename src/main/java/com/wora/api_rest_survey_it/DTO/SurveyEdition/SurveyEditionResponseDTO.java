package com.wora.api_rest_survey_it.DTO.SurveyEdition;

import com.wora.api_rest_survey_it.DTO.Survey.embd.SurveyEmbdResponse;
import com.wora.api_rest_survey_it.annotation.EXIST.Exist;
import com.wora.api_rest_survey_it.entity.Survey;
import com.wora.api_rest_survey_it.repository.SurveyRepository;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyEditionResponseDTO {

    private LocalDate creationDate;
    private LocalDate startDate;
    private Integer year;
    private SurveyEmbdResponse survey;

}
