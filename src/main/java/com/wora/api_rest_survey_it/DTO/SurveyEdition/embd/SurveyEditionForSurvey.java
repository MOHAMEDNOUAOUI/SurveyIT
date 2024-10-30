package com.wora.api_rest_survey_it.DTO.SurveyEdition.embd;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyEditionForSurvey {

    private Long id;

    private LocalDate creationDate;

    private LocalDate startDate;

    private Integer year;

}
