package com.wora.api_rest_survey_it.entity;

import com.wora.api_rest_survey_it.entity.Enum.QuestionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "question")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "questionType")
    private QuestionType questionType;

    @NotBlank
    @Column(name = "text")
    private String text;

    private Integer answerCount;

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private Subject subject;

    @OneToMany(mappedBy = "question" , fetch = FetchType.LAZY)
    private Set<Answer> answers = new HashSet<>();

}
