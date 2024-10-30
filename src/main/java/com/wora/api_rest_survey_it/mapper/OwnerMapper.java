package com.wora.api_rest_survey_it.mapper;

import com.wora.api_rest_survey_it.DTO.Owner.OwnerCreateDTO;
import com.wora.api_rest_survey_it.DTO.Owner.OwnerResponseDTO;
import com.wora.api_rest_survey_it.DTO.Owner.embd.OwnerEmbdResponse;
import com.wora.api_rest_survey_it.DTO.Owner.embd.OwnerResponseCreate;
import com.wora.api_rest_survey_it.entity.Owner;
import com.wora.api_rest_survey_it.entity.Survey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    Owner toEntity(OwnerCreateDTO ownerCreateDTO);


    OwnerResponseCreate ReturnResponseWhenCreate(Owner owner);

    OwnerEmbdResponse toReponseGet(Owner owner);


}
