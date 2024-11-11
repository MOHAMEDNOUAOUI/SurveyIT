package com.wora.api_rest_survey_it.DTO.Answer.embd;


import com.wora.api_rest_survey_it.annotation.EXIST.Exist;
import com.wora.api_rest_survey_it.entity.Answer;
import com.wora.api_rest_survey_it.repository.AnswerRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerIdDTO {

    @Exist(entity = Answer.class, repository = AnswerRepository.class)
    private Long answerId;

}
