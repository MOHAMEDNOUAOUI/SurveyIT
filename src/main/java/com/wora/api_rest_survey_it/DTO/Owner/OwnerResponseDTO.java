package com.wora.api_rest_survey_it.DTO.Owner;

import com.wora.api_rest_survey_it.DTO.Survey.SurveyCreateDTO;
import com.wora.api_rest_survey_it.DTO.Survey.embd.SurveyEmbdResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerResponseDTO {

    private Long id;
    private String name;
    private List<SurveyEmbdResponse> survey;

}
