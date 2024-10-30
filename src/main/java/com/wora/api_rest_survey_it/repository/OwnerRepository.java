package com.wora.api_rest_survey_it.repository;

import com.wora.api_rest_survey_it.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner , Long> {
    Owner findByName(String name);
}
