package com.wora.api_rest_survey_it.mapper;

import com.wora.api_rest_survey_it.DTO.Owner.OwnerCreateDTO;
import com.wora.api_rest_survey_it.DTO.Owner.embd.OwnerEmbdResponse;
import com.wora.api_rest_survey_it.DTO.Owner.embd.OwnerResponseCreate;
import com.wora.api_rest_survey_it.entity.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    @Mapping(source = "name" , target = "name")
    Owner toEntity(OwnerCreateDTO ownerCreateDTO);

    OwnerResponseCreate ReturnResponseWhenCreate(Owner owner);

    OwnerEmbdResponse toReponseGet(Owner owner);


}
