package com.wora.api_rest_survey_it.DTO.Answer;

import com.wora.api_rest_survey_it.annotation.EXIST.Exist;
import com.wora.api_rest_survey_it.annotation.UNIQUE.Unique;
import com.wora.api_rest_survey_it.entity.Answer;
import com.wora.api_rest_survey_it.entity.Question;
import com.wora.api_rest_survey_it.repository.AnswerRepository;
import com.wora.api_rest_survey_it.repository.QuestionRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerCreateDTO {

    @NotBlank
    @Unique(entity = Answer.class, repository = AnswerRepository.class, column = "Text")
    private String text;

    @NotNull
    private Integer selectionCount = 0;

//    @Exist(entity = Question.class, repository = QuestionRepository.class)
    private Long questionId;
}
