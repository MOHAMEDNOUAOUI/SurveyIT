package com.wora.api_rest_survey_it.DTO.Owner;

import com.wora.api_rest_survey_it.DTO.Survey.SurveyCreateDTO;
import com.wora.api_rest_survey_it.annotation.UNIQUE.Unique;
import com.wora.api_rest_survey_it.entity.Owner;
import com.wora.api_rest_survey_it.repository.OwnerRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerCreateDTO {

    @NotNull
    @NotEmpty
    @NotBlank
    @Unique(entity = Owner.class, repository = OwnerRepository.class, column = "Name")
    private String name;
}
