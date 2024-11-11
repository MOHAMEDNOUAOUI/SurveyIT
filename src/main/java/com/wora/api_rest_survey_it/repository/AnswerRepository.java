package com.wora.api_rest_survey_it.repository;

import com.wora.api_rest_survey_it.entity.Answer;
import com.wora.api_rest_survey_it.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer , Long> {
    Optional<Answer> findByText(String text);
}
