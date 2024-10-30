package com.wora.api_rest_survey_it.DTO.Owner;

import com.wora.api_rest_survey_it.DTO.Survey.SurveyCreateDTO;
import com.wora.api_rest_survey_it.annotation.UNIQUE.Unique;
import com.wora.api_rest_survey_it.entity.Owner;
import com.wora.api_rest_survey_it.repository.OwnerRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerCreateDTO {

    @NotBlank
    @Unique(entity = Owner.class, repository = OwnerRepository.class, column = "name")
    private String name;

}
