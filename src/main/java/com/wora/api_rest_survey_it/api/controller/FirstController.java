package com.wora.api_rest_survey_it.api.controller;


import com.wora.api_rest_survey_it.entity.Owner;
import com.wora.api_rest_survey_it.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FirstController {

    @Autowired
    private OwnerRepository ownerRepository;

    @GetMapping
    public String getOwners() {
        ownerRepository.findAll();
        return "AOklzrnvejv";
    }

}
