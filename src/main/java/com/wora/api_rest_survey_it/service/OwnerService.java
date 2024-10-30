package com.wora.api_rest_survey_it.service;

import com.wora.api_rest_survey_it.DTO.Owner.OwnerCreateDTO;
import com.wora.api_rest_survey_it.DTO.Owner.OwnerResponseDTO;
import com.wora.api_rest_survey_it.DTO.Owner.embd.OwnerEmbdResponse;
import com.wora.api_rest_survey_it.DTO.Owner.embd.OwnerResponseCreate;
import com.wora.api_rest_survey_it.entity.Owner;
import com.wora.api_rest_survey_it.entity.Survey;

import java.util.List;
import java.util.Optional;

public interface OwnerService {

    // this will return the DTO conatining id and name onlyy
    OwnerResponseCreate createOwner(OwnerCreateDTO ownerCreateDTO);
    List<OwnerEmbdResponse> getAllOwners();

}
