package com.wora.api_rest_survey_it.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "survey_edition")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyEdition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "creationDate")
    private LocalDate creationDate;

    @NotNull
    @Column(name = "startDate")
    private LocalDate startDate;

    @NotNull
    @Column(name = "year")
    private Integer year;


    @ManyToOne
    private Survey survey;

    @OneToMany(mappedBy = "surveyEdition")
    private Set<Subject> subjects = new HashSet<>();
}
