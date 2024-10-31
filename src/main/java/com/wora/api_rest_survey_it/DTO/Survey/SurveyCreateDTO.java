package com.wora.api_rest_survey_it.DTO.Survey;


import com.wora.api_rest_survey_it.annotation.EXIST.Exist;
import com.wora.api_rest_survey_it.annotation.UNIQUE.Unique;
import com.wora.api_rest_survey_it.entity.Owner;
import com.wora.api_rest_survey_it.entity.Subject;
import com.wora.api_rest_survey_it.entity.Survey;
import com.wora.api_rest_survey_it.repository.OwnerRepository;
import com.wora.api_rest_survey_it.repository.SubjectRepository;
import com.wora.api_rest_survey_it.repository.SurveyRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class    SurveyCreateDTO {

    @NotBlank
    @Unique(repository = SurveyRepository.class , entity = Survey.class, column = "Title")
    private String title;
    @NotBlank
    private String description;

    @Positive
    @NotNull
    @Exist(entity = Owner.class, repository = OwnerRepository.class)
    private Long ownerId;

}
