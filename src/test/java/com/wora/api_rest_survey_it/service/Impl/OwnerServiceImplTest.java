package com.wora.api_rest_survey_it.service.Impl;

import com.wora.api_rest_survey_it.DTO.Owner.OwnerCreateDTO;
import com.wora.api_rest_survey_it.DTO.Owner.embd.OwnerResponseCreate;
import com.wora.api_rest_survey_it.entity.Owner;
import com.wora.api_rest_survey_it.mapper.OwnerMapper;
import com.wora.api_rest_survey_it.repository.OwnerRepository;
import com.wora.api_rest_survey_it.repository.SurveyRepository;
import org.hibernate.validator.constraints.Mod10Check;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class OwnerServiceImplTest {

    @Mock
    private OwnerServiceImpl ownerService;

    @Mock
    private OwnerRepository ownerRepository;
    @Mock
    private SurveyRepository surveyRepository;
    @Mock
    private OwnerMapper ownerMapper;

    private OwnerCreateDTO ownerCreateDTO;
    private OwnerResponseCreate ownerResponseCreate;
    private Owner owner;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ownerCreateDTO = new OwnerCreateDTO();
        owner = new Owner();
        ownerResponseCreate = new OwnerResponseCreate();
        owner.setId(1L);
    }

    @Test
    void saveOwner() {
//        when(ownerMapper.toEntity(ownerCreateDTO)).thenReturn(owner);
//        when(ownerRepository.save(owner)).thenReturn(owner);
//        when(ownerMapper.ReturnResponseWhenCreate(owner)).thenReturn(ownerResponseCreate);
//
//        OwnerResponseCreate ownerResponseCreate1 = ownerService.saveOwner(ownerCreateDTO);
//
//        assertNotNull(ownerResponseCreate1, "The response should not be null");
//        assertEquals(ownerResponseCreate, ownerResponseCreate1, "The response should match the expected value");
    }

    @Test
    void getAllOwners() {
    }

    @Test
    void getOwnerById() {
    }

    @Test
    void getOwnerByName() {
    }

    @Test
    void deleteOwnerById() {
    }

    @Test
    void updateOwnerById() {
    }
}