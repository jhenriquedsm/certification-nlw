package br.com.jhenriquedsm.certification_nlw.modules.students.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhenriquedsm.certification_nlw.modules.questions.entities.QuestionEntity;
import br.com.jhenriquedsm.certification_nlw.modules.questions.repositories.QuestionRepository;
import br.com.jhenriquedsm.certification_nlw.modules.students.dto.StudentCertificationAnswerDTO;

@Service
public class StudentCertificationAnswersUseCase {

    @Autowired
    private QuestionRepository questionRepository;

    public StudentCertificationAnswerDTO execute(StudentCertificationAnswerDTO dto){
        // buscar as alternativas das perguntas
        // - correct or incorrect
        List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(dto.getTechnology());
        dto.getQuestionAnswers()
        .stream().forEach(questionAnswer -> {
            var question = questionsEntity.stream()
                .filter(q -> q.getId().equals(questionAnswer.getQuestion_id()))
                .findFirst().get();

            var findCorrectAlternative = question.getAlternatives().stream()
                .filter(alternative -> alternative.isCorrect())
                .findFirst().get();

            if(findCorrectAlternative.getId().equals(questionAnswer.getAlternative_id())) {
                questionAnswer.setCorrect(true);
            } else {
                questionAnswer.setCorrect(false);
            }
        });
        
        return dto;
        // salvar informações da certificação
    }
}
