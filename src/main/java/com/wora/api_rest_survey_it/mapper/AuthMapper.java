package com.wora.api_rest_survey_it.mapper;

import com.wora.api_rest_survey_it.DTO.Auth.RegisterDTO;
import com.wora.api_rest_survey_it.entity.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    @Mapping(source = "name" , target = "name")
    @Mapping(source = "password" , target = "password")
    @Mapping(source = "role" ,target = "role")
    Owner toEntity(RegisterDTO registerDTO);
}
