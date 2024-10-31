package com.wora.api_rest_survey_it.service;

import com.wora.api_rest_survey_it.DTO.Owner.OwnerCreateDTO;
import com.wora.api_rest_survey_it.DTO.Owner.embd.OwnerEmbdResponse;
import com.wora.api_rest_survey_it.DTO.Owner.embd.OwnerResponseCreate;

import java.util.List;

public interface OwnerService {

    // this will return the DTO conatining id and name onlyy
    OwnerResponseCreate saveOwner(OwnerCreateDTO ownerCreateDTO);
    List<OwnerEmbdResponse> getAllOwners();
    OwnerEmbdResponse getOwnerById(Long id);
    OwnerEmbdResponse getOwnerByName(String name);
    Boolean deleteOwnerById(Long id);
    OwnerResponseCreate updateOwnerById(Long id , OwnerCreateDTO ownerCreateDTO);

}
