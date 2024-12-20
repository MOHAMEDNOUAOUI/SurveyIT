package com.wora.api_rest_survey_it.service.Impl;

import com.wora.api_rest_survey_it.DTO.Answer.AnswerResponseDTO;
import com.wora.api_rest_survey_it.DTO.Question.QuestionCreateDTO;
import com.wora.api_rest_survey_it.DTO.Question.QuestionResponseDTO;
import com.wora.api_rest_survey_it.DTO.misendsituation.DTOResponse;
import com.wora.api_rest_survey_it.DTO.misendsituation.DTOcreateQuestionWithAnswers;
import com.wora.api_rest_survey_it.entity.Answer;
import com.wora.api_rest_survey_it.entity.Question;
import com.wora.api_rest_survey_it.entity.Subject;
import com.wora.api_rest_survey_it.mapper.AnswerMapper;
import com.wora.api_rest_survey_it.mapper.QuestionMapper;
import com.wora.api_rest_survey_it.repository.AnswerRepository;
import com.wora.api_rest_survey_it.repository.QuestionRepository;
import com.wora.api_rest_survey_it.repository.SubjectRepository;
import com.wora.api_rest_survey_it.service.AnswerService;
import com.wora.api_rest_survey_it.service.QuestionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public QuestionResponseDTO createQuestion(QuestionCreateDTO questionCreateDTO) {
        Question question = questionMapper.toQuestion(questionCreateDTO);
        if (subjectRepository.existsById(questionCreateDTO.getSubjectId())){
            Subject subject = subjectRepository.getReferenceById(questionCreateDTO.getSubjectId());
            if(subject.getParent() == null){
                throw new RuntimeException("You can not add question to the main subject");
            }

            question.setSubject(subject);
            Question questionsaved = questionRepository.save(question);
            return questionMapper.toResponse(questionsaved);
        }else{
            throw new EntityNotFoundException("Subject was not found");
        }

    }

    @Override
    public List<QuestionResponseDTO> findAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) {
            throw new RuntimeException("No Questions found");
        }
        return questions.stream().map(questionMapper::toResponse).toList();
    }

    @Override
    public QuestionResponseDTO findQuestionById(Long id) {
        if(questionRepository.existsById(id)){
            Question question = questionRepository.getReferenceById(id);
            return questionMapper.toResponse(question);
        }else{
            throw new EntityNotFoundException("Question not found");
        }
    }

    @Override
    public boolean deleteQuestion(Long id) {
        if(questionRepository.existsById(id)){
            Question question = questionRepository.getReferenceById(id);
            questionRepository.delete(question);
            return true;
        }else{
            throw new EntityNotFoundException("Question not found");
        }
    }

    @Override
    public QuestionResponseDTO updateQuestion(Long id, QuestionCreateDTO questionCreateDTO) {
        if (!questionRepository.existsById(id)){
            throw new EntityNotFoundException("Question was not found");
        }

        ValidateData(questionCreateDTO);

        Question existedQuestion = questionRepository.getReferenceById(id);

        Optional.ofNullable(questionCreateDTO.getText())
                .filter(newText -> !newText.equals(existedQuestion.getText()))
                .ifPresent(existedQuestion::setText);

        Optional.ofNullable(questionCreateDTO.getQuestionType())
                .filter(newType -> !newType.equals(existedQuestion.getQuestionType()))
                .ifPresent(existedQuestion::setQuestionType);

        Optional.ofNullable(questionCreateDTO.getSubjectId())
                .filter(newSubjectId -> !newSubjectId.equals(existedQuestion.getSubject().getId()))
                .ifPresent(newSubjectId -> {
                    Subject newSubject = subjectRepository.findById(newSubjectId)
                            .orElseThrow(() -> new EntityNotFoundException("Subject not found"));
                    existedQuestion.setSubject(newSubject);
                });

        Question updatedQuestion = questionRepository.save(existedQuestion);
        return questionMapper.toResponse(updatedQuestion);
    }

    @Override
    public DTOResponse createWithAnswers(DTOcreateQuestionWithAnswers dtOcreateQuestionWithAnswers) {
        QuestionResponseDTO questionResponseDTO = createQuestion(dtOcreateQuestionWithAnswers.getQuestion());
        List<AnswerResponseDTO> answers = dtOcreateQuestionWithAnswers.getAnswers().stream().map(answerService::createAnswer).toList();
        DTOResponse response = new DTOResponse();
        response.setQuestionResponseDTO(questionResponseDTO);
        response.setAnswerResponseDTO(answers);
        return response;
    }


    public void ValidateData(QuestionCreateDTO questionCreateDTO){
        if (questionCreateDTO.getText() == null
            && questionCreateDTO.getSubjectId() == null
            && questionCreateDTO.getQuestionType() == null){
            throw new RuntimeException("Data was not provided");
        }
    }


}
