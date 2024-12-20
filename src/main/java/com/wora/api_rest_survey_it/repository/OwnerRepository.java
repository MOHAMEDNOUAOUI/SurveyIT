package com.wora.api_rest_survey_it.repository;

import com.wora.api_rest_survey_it.entity.Owner;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner , Long> {
    Optional<Owner> findByName(String name);
}
