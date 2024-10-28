package com.wora.api_rest_survey_it.repository;

import com.wora.api_rest_survey_it.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject , Long> {
}
