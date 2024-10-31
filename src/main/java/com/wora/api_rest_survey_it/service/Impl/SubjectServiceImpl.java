package com.wora.api_rest_survey_it.service.Impl;

import com.wora.api_rest_survey_it.DTO.Subject.SubjectCreateDTO;
import com.wora.api_rest_survey_it.DTO.Subject.SubjectResponseDTO;
import com.wora.api_rest_survey_it.entity.Subject;
import com.wora.api_rest_survey_it.entity.SurveyEdition;
import com.wora.api_rest_survey_it.mapper.SubjectMapper;
import com.wora.api_rest_survey_it.repository.SubjectRepository;
import com.wora.api_rest_survey_it.repository.SurveyEditionRepository;
import com.wora.api_rest_survey_it.service.SubjectService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private SurveyEditionRepository surveyEditionRepository;
    @Autowired
    private SubjectMapper subjectMapper;


    @Override
    public SubjectResponseDTO createSubject(SubjectCreateDTO subjectCreateDTO) {
        Subject parent = null;
        SurveyEdition surveyEdition = null;
        Subject createdSubject = subjectMapper.toSubject(subjectCreateDTO);

        if (subjectCreateDTO.getSurveyEditionId() != null) {
            Long SurveyEditionId = subjectCreateDTO.getSurveyEditionId();
            if (surveyEditionRepository.existsById(SurveyEditionId)) {
                surveyEdition = surveyEditionRepository.findById(SurveyEditionId).get();
                createdSubject.setSurveyEdition(surveyEdition);
            } else {
                throw new RuntimeException("Survey Edition with the id " + SurveyEditionId + " Does not exist");
            }

        } else {
            throw new RuntimeException("Survey Edition id must be provided");
        }


        if (subjectCreateDTO.getParentId() != null) {
            if (subjectRepository.existsById(subjectCreateDTO.getParentId())) {
                parent = subjectRepository.findById(subjectCreateDTO.getParentId()).get();
                createdSubject.setParent(parent);
            } else {
                throw new EntityNotFoundException("Subject Parent with the id " + subjectCreateDTO.getParentId() + " does not exist");
            }
        }

        Subject saveSubject = subjectRepository.save(createdSubject);
        return subjectMapper.toResponseSubject(saveSubject);

    }
}
