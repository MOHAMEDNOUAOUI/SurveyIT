package com.wora.api_rest_survey_it.repository;

import com.wora.api_rest_survey_it.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question , Long> {
}
