package com.wora.api_rest_survey_it.repository;

import com.wora.api_rest_survey_it.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SurveyRepository extends JpaRepository<Survey , Long> {
    Optional<Survey> findByTitle(String title);
    boolean existsByTitle(String title);
}
