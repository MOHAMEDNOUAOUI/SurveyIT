package com.wora.api_rest_survey_it.service;

import com.wora.api_rest_survey_it.DTO.Subject.SubjectCreateDTO;
import com.wora.api_rest_survey_it.DTO.Subject.SubjectResponseDTO;

public interface SubjectService {
    SubjectResponseDTO createSubject(SubjectCreateDTO subjectCreateDTO);
}
