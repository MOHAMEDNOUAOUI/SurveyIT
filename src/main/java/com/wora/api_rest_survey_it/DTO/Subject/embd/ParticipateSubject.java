package com.wora.api_rest_survey_it.DTO.Subject.embd;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipateSubject {
    private String title;
    private List<SubSubjectDTO> subSubjects;
}
