package com.wora.api_rest_survey_it.service;

import com.wora.api_rest_survey_it.DTO.Subject.SubjectCreateDTO;
import com.wora.api_rest_survey_it.DTO.Subject.SubjectResponseDTO;
import com.wora.api_rest_survey_it.DTO.Subject.embd.MainSubjectResponse;

import java.util.List;

public interface SubjectService {
    SubjectResponseDTO createSubject(SubjectCreateDTO subjectCreateDTO);
    List<SubjectResponseDTO> findAllSubjects();
    List<MainSubjectResponse> findAllMainSubjects();

    Object findSubjectById(Long id);

    boolean deleteById(Long id);


    Object updateSubject(Long id , SubjectCreateDTO subjectCreateDTO);


}
