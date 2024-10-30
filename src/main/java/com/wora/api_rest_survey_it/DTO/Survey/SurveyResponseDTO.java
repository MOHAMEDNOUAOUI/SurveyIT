package com.wora.api_rest_survey_it.DTO.Survey;

import com.wora.api_rest_survey_it.DTO.Owner.embd.OwnerEmbdResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResponseDTO {

    private Long id;
    private String title;
    private String description;
    private OwnerEmbdResponse owner;

}
