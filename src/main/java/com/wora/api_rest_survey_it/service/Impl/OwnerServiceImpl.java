package com.wora.api_rest_survey_it.service.Impl;

import com.wora.api_rest_survey_it.DTO.Owner.OwnerCreateDTO;
import com.wora.api_rest_survey_it.DTO.Owner.embd.OwnerEmbdResponse;
import com.wora.api_rest_survey_it.DTO.Owner.embd.OwnerResponseCreate;
import com.wora.api_rest_survey_it.entity.Owner;
import com.wora.api_rest_survey_it.mapper.OwnerMapper;
import com.wora.api_rest_survey_it.repository.OwnerRepository;
import com.wora.api_rest_survey_it.repository.SurveyRepository;
import com.wora.api_rest_survey_it.service.OwnerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private OwnerMapper ownerMapper;



    @Override
    public OwnerResponseCreate createOwner(OwnerCreateDTO ownerCreateDTO){
        Owner toEntityOwner = ownerMapper.toEntity(ownerCreateDTO);
        Owner owner = ownerRepository.save(toEntityOwner);
        return ownerMapper.ReturnResponseWhenCreate(owner);
    }

    @Override
    public List<OwnerEmbdResponse> getAllOwners() {
        List<Owner> ownerList = ownerRepository.findAll();
        return ownerList.stream().map(ownerMapper::toReponseGet).toList();
    }

}
