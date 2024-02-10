package br.com.jhenriquedsm.certification_nlw.modules.students.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jhenriquedsm.certification_nlw.modules.students.entities.CertificationsStudentEntity;

@Repository
public interface CertificationStudentRepository extends JpaRepository<CertificationsStudentEntity, UUID>{

    @Query("SELECT c FROM certifications c INNER JOIN c.studentEntity std WHERE std.email = :email AND c.technology = :technology")
    List<CertificationsStudentEntity> findByStudentEmailAndTechnology(String email, String technology);

    @Query("SELECT c FROM certifications c ORDER BY c.grade DESC LIMIT 10")
    List<CertificationsStudentEntity> findTop10ByOrderByGradeDesc();
}
