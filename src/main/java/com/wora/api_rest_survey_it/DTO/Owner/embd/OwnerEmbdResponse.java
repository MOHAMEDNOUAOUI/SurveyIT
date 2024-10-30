package com.wora.api_rest_survey_it.DTO.Owner.embd;

import com.wora.api_rest_survey_it.DTO.Survey.embd.SurveyEmbdResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerEmbdResponse {

    private Long id;
    private String name;
    private List<SurveyEmbdResponse> survey;

}
