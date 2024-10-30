package com.wora.api_rest_survey_it.DTO.Survey;


import com.wora.api_rest_survey_it.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyCreateDTO {

    private Long id;
    private String title;
    private String description;

}
