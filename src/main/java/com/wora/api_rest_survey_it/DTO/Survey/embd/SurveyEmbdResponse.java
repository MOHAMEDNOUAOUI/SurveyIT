package com.wora.api_rest_survey_it.DTO.Survey.embd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyEmbdResponse {

    private Long id;
    private String title;
    private String description;

}
