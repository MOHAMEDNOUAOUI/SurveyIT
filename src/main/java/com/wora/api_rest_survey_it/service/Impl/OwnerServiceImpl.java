package com.wora.api_rest_survey_it.service.Impl;

import com.wora.api_rest_survey_it.DTO.Owner.OwnerCreateDTO;
import com.wora.api_rest_survey_it.DTO.Owner.embd.OwnerEmbdResponse;
import com.wora.api_rest_survey_it.DTO.Owner.embd.OwnerResponseCreate;
import com.wora.api_rest_survey_it.entity.Owner;
import com.wora.api_rest_survey_it.mapper.OwnerMapper;
import com.wora.api_rest_survey_it.repository.OwnerRepository;
import com.wora.api_rest_survey_it.repository.SurveyRepository;
import com.wora.api_rest_survey_it.service.OwnerService;
import jakarta.persistence.EntityNotFoundException;
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
    public OwnerResponseCreate saveOwner(OwnerCreateDTO ownerCreateDTO){
        Owner toEntityOwner = ownerMapper.toEntity(ownerCreateDTO);
        Owner owner = ownerRepository.save(toEntityOwner);
        return ownerMapper.ReturnResponseWhenCreate(owner);
    }

    @Override
    public List<OwnerEmbdResponse> getAllOwners() {
        List<Owner> ownerList = ownerRepository.findAll();
        return ownerList.stream().map(ownerMapper::toReponseGet).toList();
    }

    @Override
    public OwnerEmbdResponse getOwnerById(Long ownerId){
        if(ownerRepository.existsById(ownerId)){
            Owner owner = ownerRepository.findById(ownerId).get();
            return ownerMapper.toReponseGet(owner);
        }else{
         throw new EntityNotFoundException("Thie Owner with the id " + ownerId + " doesn not exist");
        }
    }

    @Override
    public OwnerEmbdResponse getOwnerByName(String name) {
        Optional<Owner> owner = ownerRepository.findByName(name);
        if(owner.isPresent()){
            return ownerMapper.toReponseGet(owner.get());
        }else {
            throw new EntityNotFoundException("This Owner does not exist");
        }
    }

    @Override
    public Boolean deleteOwnerById(Long id) {
        try{
            Optional<Owner> owner = ownerRepository.findById(id);
           if(owner.isPresent()){
               ownerRepository.delete(owner.get());
               return true;
           }else{
               return false;
           }
        }catch (EntityNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OwnerResponseCreate updateOwnerById(Long id, OwnerCreateDTO ownerCreateDTO) {
        if (ownerRepository.existsById(id)){
            //to entity
            Owner owner = ownerMapper.toEntity(ownerCreateDTO);
            owner.setId(id);
            Owner updatedOwner = ownerRepository.save(owner);
            return ownerMapper.ReturnResponseWhenCreate(updatedOwner);
        }else {
            throw new RuntimeException("Something happend will deleting this owner");
        }

    }

}
