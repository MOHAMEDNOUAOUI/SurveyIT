package com.wora.api_rest_survey_it.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotBlank
    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Subject parent;

    @ManyToOne(fetch = FetchType.LAZY)
    private SurveyEdition surveyEdition;

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "subject" , cascade = CascadeType.ALL)
    private Set<Question> questions = new HashSet<>();
}
