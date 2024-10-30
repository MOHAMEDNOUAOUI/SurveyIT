package com.wora.api_rest_survey_it.controller;


import com.wora.api_rest_survey_it.DTO.Owner.OwnerCreateDTO;
import com.wora.api_rest_survey_it.DTO.Owner.embd.OwnerEmbdResponse;
import com.wora.api_rest_survey_it.DTO.Owner.embd.OwnerResponseCreate;
import com.wora.api_rest_survey_it.annotation.EXIST.Exist;
import com.wora.api_rest_survey_it.entity.Owner;
import com.wora.api_rest_survey_it.repository.OwnerRepository;
import com.wora.api_rest_survey_it.service.OwnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @PostMapping
    public OwnerResponseCreate createOwner(@Valid @RequestBody  OwnerCreateDTO ownerCreateDTO) {
        return ownerService.saveOwner(ownerCreateDTO);
    }

    @GetMapping
    public List<OwnerEmbdResponse> getAllOwners() {
       return ownerService.getAllOwners();
    }

    @GetMapping("/{ownerId}")
    public OwnerEmbdResponse getOwnerById(
            @PathVariable("ownerId")
            @Exist(message = "Owner with ID ${validatedValue} does not exist", entity = Owner.class, repository = OwnerRepository.class)  Long id){
        return ownerService.getOwnerById(id);
    }

}
