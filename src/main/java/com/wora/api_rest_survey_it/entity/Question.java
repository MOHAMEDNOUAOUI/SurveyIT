package com.wora.api_rest_survey_it.entity;

import com.wora.api_rest_survey_it.entity.Enum.QuestionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.HashSet;
import java.util.List;
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

    @ColumnDefault("0")
    private Integer answerCount = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    @OneToMany(mappedBy = "question" , fetch = FetchType.EAGER , cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

}
