package com.wora.api_rest_survey_it.repository;

import com.wora.api_rest_survey_it.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject , Long> {
    Optional<Subject> findByTitle(String title);
    List<Subject> findAllByParentIdIsNull();
    List<Subject> findAllByParentId(Long id);
}
