package com.wora.api_rest_survey_it.DTO.Response;

import com.wora.api_rest_survey_it.DTO.Subject.embd.ParticipateSubject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipateResponseDTO {
    private String surveyTitle;
    private List<ParticipateSubject> subjects;
}
