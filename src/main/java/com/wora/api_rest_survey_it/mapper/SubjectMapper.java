package com.wora.api_rest_survey_it.mapper;

import com.wora.api_rest_survey_it.DTO.Subject.SubjectCreateDTO;
import com.wora.api_rest_survey_it.DTO.Subject.SubjectResponseDTO;
import com.wora.api_rest_survey_it.DTO.Subject.embd.MainSubjectResponse;
import com.wora.api_rest_survey_it.DTO.Subject.embd.SubjectEmbdResponseDTO;
import com.wora.api_rest_survey_it.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    Subject toSubject(SubjectCreateDTO subjectCreateDTO);

    @Mapping(source = "parent" , target = "parent")
    @Mapping(source = "surveyEdition" , target = "surveyEdition")
    @Mapping(source = "questionList" , target = "questionList")
    SubjectResponseDTO toResponseSubject(Subject subject);

    SubjectEmbdResponseDTO toSubSubject(Subject subject);

    MainSubjectResponse toMainSubjectResponse(Subject subject);

}
