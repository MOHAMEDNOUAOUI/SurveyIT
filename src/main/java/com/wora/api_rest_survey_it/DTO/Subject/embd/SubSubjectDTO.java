package com.wora.api_rest_survey_it.DTO.Subject.embd;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubSubjectDTO {
    private String title;
    private String question;
    private Map<String , Integer> answers;
    private int totalAnswers;
}
