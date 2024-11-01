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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;
    @Autowired
    private OwnerRepository ownerRepository;

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

    @GetMapping("/ByName/{ownerName}")
    public OwnerEmbdResponse getOwnerByName(@PathVariable("ownerName") String name){
        return ownerService.getOwnerByName(name);
    }

    @DeleteMapping("/{ownerId}")
    public ResponseEntity<?> deleteOwner(@PathVariable("ownerId") @Exist(entity = Owner.class, repository = OwnerRepository.class) Long id){
        if(ownerService.deleteOwnerById(id)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Owner not found or could not be deleted");
        }
    }

    @PutMapping("/{ownerId}")
    public OwnerResponseCreate updateOwnerById(
            @PathVariable("ownerId")
            @Exist(entity = Owner.class, repository = OwnerRepository.class , message = "The Owner with this id does not exist") Long id,
            @RequestBody @Valid OwnerCreateDTO ownerCreateDTO)
    {
        return ownerService.updateOwnerById(id , ownerCreateDTO);
    }


}
