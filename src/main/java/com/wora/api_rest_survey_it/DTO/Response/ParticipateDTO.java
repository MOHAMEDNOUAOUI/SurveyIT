package com.wora.api_rest_survey_it.DTO.Response;

import com.wora.api_rest_survey_it.DTO.Answer.embd.AnswerIdDTO;
import com.wora.api_rest_survey_it.annotation.EXIST.Exist;
import com.wora.api_rest_survey_it.entity.Answer;
import com.wora.api_rest_survey_it.entity.Question;
import com.wora.api_rest_survey_it.repository.AnswerRepository;
import com.wora.api_rest_survey_it.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipateDTO {

    @Exist(entity = Question.class, repository = QuestionRepository.class)
    private Long questionId;
    
    @Exist(entity = Answer.class, repository = AnswerRepository.class)
    private Long answerId;

    private List<AnswerIdDTO> answers;

    public boolean isMultiAnswer() {
        return answers != null && !answers.isEmpty();
    }

}
