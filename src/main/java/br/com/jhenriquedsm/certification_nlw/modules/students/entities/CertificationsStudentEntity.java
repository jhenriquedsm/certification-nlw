package br.com.jhenriquedsm.certification_nlw.modules.students.entities;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificationsStudentEntity {
    
    private UUID id;
    private UUID student_id;
    private String technology;
    private int grate;
    List<AnswersCertificationsEntity> answersCertificationsEntities;
}