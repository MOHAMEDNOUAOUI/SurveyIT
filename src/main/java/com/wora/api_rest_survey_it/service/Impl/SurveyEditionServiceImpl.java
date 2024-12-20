package com.wora.api_rest_survey_it.service.Impl;

import com.wora.api_rest_survey_it.DTO.Answer.AnswerResponseDTO;
import com.wora.api_rest_survey_it.DTO.Answer.embd.AnswerEmbdReponseDTO;
import com.wora.api_rest_survey_it.DTO.Answer.embd.AnswerIdDTO;
import com.wora.api_rest_survey_it.DTO.Question.QuestionResponseDTO;
import com.wora.api_rest_survey_it.DTO.Response.ParticipateDTO;
import com.wora.api_rest_survey_it.DTO.Response.ParticipateResponseDTO;
import com.wora.api_rest_survey_it.DTO.Subject.embd.ParticipateSubject;
import com.wora.api_rest_survey_it.DTO.Subject.embd.SubSubjectDTO;
import com.wora.api_rest_survey_it.DTO.Survey.SurveyCreateDTO;
import com.wora.api_rest_survey_it.DTO.SurveyEdition.SurveyEditionCreateDTO;
import com.wora.api_rest_survey_it.DTO.SurveyEdition.SurveyEditionResponseDTO;
import com.wora.api_rest_survey_it.ServletInitializer;
import com.wora.api_rest_survey_it.entity.Answer;
import com.wora.api_rest_survey_it.entity.Enum.QuestionType;
import com.wora.api_rest_survey_it.entity.Question;
import com.wora.api_rest_survey_it.entity.Survey;
import com.wora.api_rest_survey_it.entity.SurveyEdition;
import com.wora.api_rest_survey_it.mapper.SurveyEditionMapper;
import com.wora.api_rest_survey_it.repository.*;
import com.wora.api_rest_survey_it.service.QuestionService;
import com.wora.api_rest_survey_it.service.SubjectService;
import com.wora.api_rest_survey_it.service.SurveyEditionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SurveyEditionServiceImpl implements SurveyEditionService {

    @Autowired
    private SurveyEditionRepository surveyEditionRepository;
    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private SurveyEditionMapper surveyEditionMapper;
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private SubjectService subjectService;

    @Override
    public SurveyEditionResponseDTO saveSurveyEdition(SurveyEditionCreateDTO surveyEditionCreateDTO) {
        SurveyEdition surveyEdition = surveyEditionMapper.toSurveyEdition(surveyEditionCreateDTO);
        SurveyEdition saveSurveyEdition = null;
        if (surveyEditionCreateDTO.getSurveyId() != null) {
            Survey survey = surveyRepository.findById(surveyEditionCreateDTO.getSurveyId())
                    .orElseThrow(() -> new RuntimeException("Survey id not found"));
            surveyEdition.setSurvey(survey);
            saveSurveyEdition = surveyEditionRepository.save(surveyEdition);
        }
        return surveyEditionMapper.convertToSurveyEditionResponseDTO(saveSurveyEdition);
    }

    @Override
    public List<SurveyEditionResponseDTO> getAllSurveyEditions() {
        List<SurveyEdition> surveyEditionList = surveyEditionRepository.findAll();
        if (surveyEditionList.isEmpty()) {
            throw new RuntimeException("No Surveys Edition Found");
        }
        return surveyEditionList.stream().map(surveyEditionMapper::convertToSurveyEditionResponseDTO).toList();
    }

    @Override
    public SurveyEditionResponseDTO findSurveyEditionById(Long id) {
        if (surveyEditionRepository.existsById(id)) {
            SurveyEdition surveyEdition = surveyEditionRepository.findById(id).get();
            return surveyEditionMapper.convertToSurveyEditionResponseDTO(surveyEdition);
        } else {
            throw new EntityNotFoundException("Couldnt find a survey edition with the id " + id);
        }
    }

    @Override
    public boolean deleteSurveyEditionById(Long id) {
        if (surveyEditionRepository.existsById(id)) {
            SurveyEdition surveyEdition = surveyEditionRepository.findById(id).get();
            surveyEditionRepository.delete(surveyEdition);
            return true;
        }
        return false;
    }

    @Override
    public SurveyEditionResponseDTO updateSurveyEdition(Long id, SurveyEditionCreateDTO surveyEditionCreateDTO) {
//        SurveyEdition createSurveyEdition = surveyEditionMapper.toSurveyEdition(surveyEditionCreateDTO);

        SurveyEdition existSurveyEdition = surveyEditionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Survey Edition was not found with the id " + id));

        ValidateUpdateData(surveyEditionCreateDTO);
        updateSurveyEditionFields(existSurveyEdition, surveyEditionCreateDTO);

        SurveyEdition updatedSurveyEdition = surveyEditionRepository.save(existSurveyEdition);
        return surveyEditionMapper.convertToSurveyEditionResponseDTO(updatedSurveyEdition);
    }


    public void ValidateUpdateData(SurveyEditionCreateDTO surveyEditionCreateDTO) {
        if (surveyEditionCreateDTO.getSurveyId() == null && surveyEditionCreateDTO.getCreationDate() == null
                && surveyEditionCreateDTO.getYear() == null && surveyEditionCreateDTO.getStartDate() == null) {
            throw new RuntimeException("No data Provided to be updated , no need to update");
        }
    }

    public void updateSurveyEditionFields(SurveyEdition existSurveyEdition, SurveyEditionCreateDTO surveyEditionCreateDTO) {

        Optional.ofNullable(surveyEditionCreateDTO.getCreationDate())
                .filter(localDate -> !localDate.equals(existSurveyEdition.getCreationDate()))
                .ifPresent(existSurveyEdition::setCreationDate);

        Optional.ofNullable(surveyEditionCreateDTO.getYear())
                .filter(Integer -> !Integer.equals(existSurveyEdition.getYear()))
                .ifPresent(existSurveyEdition::setYear);

        Optional.ofNullable(surveyEditionCreateDTO.getStartDate())
                .filter(localDate -> !localDate.equals(existSurveyEdition.getStartDate()))
                .ifPresent(existSurveyEdition::setStartDate);

        if (surveyEditionCreateDTO.getSurveyId() != null && !surveyEditionCreateDTO.getSurveyId().equals(existSurveyEdition.getSurvey().getId())) {
            if (surveyRepository.existsById(surveyEditionCreateDTO.getSurveyId())) {
                Survey survey = surveyRepository.getReferenceById(surveyEditionCreateDTO.getSurveyId());
                existSurveyEdition.setSurvey(survey);
            } else {
                throw new EntityNotFoundException("Survey with the id " + surveyEditionCreateDTO.getSurveyId() + " was not found");
            }
        }

    }


    // participation

    @Override
    public void participateInSurvey(Long id, List<ParticipateDTO> response) {
        if (!surveyEditionRepository.existsById(id)) {
            throw new EntityNotFoundException("Survey Edition does not exist");
        }

        SurveyEdition surveyEdition = surveyEditionRepository.getReferenceById(id);

        for (ParticipateDTO singleResponse : response) {

            Long questionId = singleResponse.getQuestionId();

            if (questionId == null || !questionRepository.existsById(questionId)) {
                throw new EntityNotFoundException("The Question with the ID " + questionId + " does not exist");
            }


            Question question = questionRepository.getReferenceById(questionId);

            if (!question.getSubject().getSurveyEdition().getId().equals(id)){
                    throw new RuntimeException("this question does not belong to the Survey Edition with the id " + id +" it belongs to id " + question.getSubject().getSurveyEdition().getId());
            }

            if (singleResponse.isMultiAnswer() && question.getQuestionType().equals(QuestionType.MULTIPLE_CHOICE)) {

                List<Answer> answers = singleResponse.getAnswers().stream().map(answerIdDTO -> {
                    Long answerId = answerIdDTO.getAnswerId();
                    if (answerId == null || !answerRepository.existsById(answerId)){
                        throw new EntityNotFoundException("The answer with the id " + answerId + " does not exist");
                    }
                    return answerRepository.getReferenceById(answerId);
                }).toList();

                processMultiAnswer(question, answers);
            } else if (!singleResponse.isMultiAnswer() && question.getQuestionType().equals(QuestionType.SINGLE_CHOICE)){
                Long answerId = singleResponse.getAnswerId();
                if (answerId == null || !answerRepository.existsById(answerId)) {
                    throw new EntityNotFoundException("The answer with ID " + answerId + " does not exist");
                }
                Answer answer = answerRepository.getReferenceById(answerId);
                processSingleAnswer(question, answer);
            }
            else {
                throw new RuntimeException("You cant add multiple answers to A question with type single answer , or something went wrong");
            }

            questionRepository.save(question);
        }

    }




    public void processSingleAnswer(Question question, Answer answer) {
        question.setAnswerCount(question.getAnswerCount() + 1);
        answer.setSelectionCount(answer.getSelectionCount() + 1);
        answerRepository.save(answer);
    }

    public void processMultiAnswer(Question question, List<Answer> answers) {
        question.setAnswerCount(question.getAnswerCount() + answers.size());
        for (Answer answer : answers){
            answer.setSelectionCount(answer.getSelectionCount() + 1);
            answerRepository.save(answer);
        }
    }



    //response of participation

    @Override
    public ParticipateResponseDTO getSurveyResult(Long id) {
        if (!surveyEditionRepository.existsById(id)){
            throw new EntityNotFoundException("Survey does not exist");
        }
        SurveyEdition MainSurveyEdition = surveyEditionRepository.getReferenceById(id);

        ParticipateResponseDTO responseDTO = new ParticipateResponseDTO();

        responseDTO.setSurveyTitle(MainSurveyEdition.getSurvey().getTitle());

        List<ParticipateSubject> subjects = subjectService.findAllMainSubjects().stream()
             .map(subject -> {
                ParticipateSubject participateSubject = new ParticipateSubject();
                participateSubject.setTitle(subject.getTitle());

                List<SubSubjectDTO> subSubjects = subject.getSubSubjects().stream()
                        .map(subSubject -> {
                            SubSubjectDTO subSubjectDTO = new SubSubjectDTO();
                            subSubjectDTO.setTitle(subSubject.getTitle());

                            Question question = questionRepository.findBySubjectId(subSubject.getId())
                                    .orElseThrow(() -> new EntityNotFoundException("Question wan not found"));


                            //this is the question with the answer list
                            QuestionResponseDTO questionResponseDTO = questionService.findQuestionById(question.getId());

                            subSubjectDTO.setQuestion(question.getText());
                            subSubjectDTO.setTotalAnswers(questionResponseDTO.getAnswerCount());
                            Map<String , Integer> answersMap = questionResponseDTO.getAnswerList().stream()
                                    .collect(Collectors.toMap(
                                            AnswerEmbdReponseDTO::getText,
                                            AnswerEmbdReponseDTO::getSelectionCount
                                    ));
                            subSubjectDTO.setAnswers(answersMap);
                            return  subSubjectDTO;
                        }).toList();

                participateSubject.setSubSubjects(subSubjects);
                return participateSubject;
             }).toList();

        responseDTO.setSubjects(subjects);
        return responseDTO;
    }

//    @Override
//    public ParticipateResponseDTO getSurveyResult(Long id){
//        return null;
//    }

}
