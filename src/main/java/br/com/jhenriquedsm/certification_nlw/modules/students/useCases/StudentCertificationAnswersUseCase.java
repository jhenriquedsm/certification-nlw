package br.com.jhenriquedsm.certification_nlw.modules.students.useCases;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhenriquedsm.certification_nlw.modules.questions.entities.QuestionEntity;
import br.com.jhenriquedsm.certification_nlw.modules.questions.repositories.QuestionRepository;
import br.com.jhenriquedsm.certification_nlw.modules.students.dto.StudentCertificationAnswerDTO;
import br.com.jhenriquedsm.certification_nlw.modules.students.entities.AnswersCertificationsEntity;
import br.com.jhenriquedsm.certification_nlw.modules.students.entities.CertificationsStudentEntity;
import br.com.jhenriquedsm.certification_nlw.modules.students.entities.StudentEntity;
import br.com.jhenriquedsm.certification_nlw.modules.students.repositories.CertificationStudentRepository;
import br.com.jhenriquedsm.certification_nlw.modules.students.repositories.StudentRepository;

@Service
public class StudentCertificationAnswersUseCase {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired 
    private CertificationStudentRepository certificationStudentRepository;

    public CertificationsStudentEntity execute(StudentCertificationAnswerDTO dto){
        // buscar as alternativas das perguntas
        // - correct or incorrect
        List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(dto.getTechnology());
        List<AnswersCertificationsEntity> answersCertifications = new ArrayList<>();

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
                
                var answersCertificationsEntities = AnswersCertificationsEntity.builder()
                    .answer_id(questionAnswer.getAlternative_id())
                    .question_id(questionAnswer.getQuestion_id())
                    .isCorrect(questionAnswer.isCorrect())
                    .build();

                answersCertifications.add(answersCertificationsEntities);
        });

        // Verificar se existe student
        var student = studentRepository.findByEmail(dto.getEmail());
        UUID student_id;
        if(student.isEmpty()) {
            var studentCreated = StudentEntity.builder().email(dto.getEmail()).build();
            studentCreated = studentRepository.save(studentCreated);
            student_id = studentCreated.getId();
        } else {
            student_id = student.get().getId();
        }

        CertificationsStudentEntity certificationsStudentEntity = CertificationsStudentEntity.builder()
            .technology(dto.getTechnology())
            .student_id(student_id)
            .build();

            var certificationStudentCreated = certificationStudentRepository.save(certificationsStudentEntity);

            answersCertifications.stream().forEach(answerCerotification -> {
                answerCerotification.setCertification_id(certificationsStudentEntity.getId());
                answerCerotification.setCertificationsStudentEntity(certificationsStudentEntity);
            });

            certificationsStudentEntity.setAnswersCertificationsEntities(answersCertifications);

            certificationStudentRepository.save(certificationsStudentEntity);

        return certificationStudentCreated;
        // salvar informações da certificação
    }
}
