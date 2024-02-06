package br.com.jhenriquedsm.certification_nlw.modules.students.entities;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswersCertificationsEntity {
    
    private UUID id;
    private UUID certification_id;
    private UUID student_id;
    private UUID questions_id;
    private UUID answer_id;
    private boolean isCorrect;
}
