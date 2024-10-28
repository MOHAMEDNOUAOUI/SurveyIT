package com.wora.api_rest_survey_it.repository;

import com.wora.api_rest_survey_it.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer , Long> {
}
