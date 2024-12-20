package com.wora.api_rest_survey_it.DTO.misendsituation;

import com.wora.api_rest_survey_it.DTO.Answer.AnswerCreateDTO;
import com.wora.api_rest_survey_it.DTO.Question.QuestionCreateDTO;
import com.wora.api_rest_survey_it.annotation.EXIST.Exist;
import com.wora.api_rest_survey_it.annotation.UNIQUE.Unique;
import com.wora.api_rest_survey_it.entity.Enum.QuestionType;
import com.wora.api_rest_survey_it.entity.Question;
import com.wora.api_rest_survey_it.entity.Subject;
import com.wora.api_rest_survey_it.repository.QuestionRepository;
import com.wora.api_rest_survey_it.repository.SubjectRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOcreateQuestionWithAnswers {

    private QuestionCreateDTO question;
    private List<AnswerCreateDTO> answers;
}
