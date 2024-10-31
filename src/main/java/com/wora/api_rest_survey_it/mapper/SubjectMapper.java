package com.wora.api_rest_survey_it.mapper;

import com.wora.api_rest_survey_it.DTO.Subject.SubjectCreateDTO;
import com.wora.api_rest_survey_it.DTO.Subject.SubjectResponseDTO;
import com.wora.api_rest_survey_it.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    Subject toSubject(SubjectCreateDTO subjectCreateDTO);

    @Mapping(source = "parent" , target = "parent")
    @Mapping(source = "surveyEdition" , target = "surveyEdition")
    SubjectResponseDTO toResponseSubject(Subject subject);

}
