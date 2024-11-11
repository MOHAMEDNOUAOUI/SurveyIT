package com.wora.api_rest_survey_it.DTO.Question;


import com.wora.api_rest_survey_it.annotation.EXIST.Exist;
import com.wora.api_rest_survey_it.annotation.UNIQUE.Unique;
import com.wora.api_rest_survey_it.entity.Enum.QuestionType;
import com.wora.api_rest_survey_it.entity.Question;
import com.wora.api_rest_survey_it.entity.Subject;
import com.wora.api_rest_survey_it.repository.QuestionRepository;
import com.wora.api_rest_survey_it.repository.SubjectRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCreateDTO {

    @NotBlank
    @Unique(entity = Question.class, repository = QuestionRepository.class, column = "Text")
    private String text;

    private QuestionType questionType;

    @Exist(entity = Subject.class, repository = SubjectRepository.class)
    private Long subjectId;

}
