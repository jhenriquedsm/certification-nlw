package br.com.jhenriquedsm.certification_nlw.modules.students.useCases;

import org.springframework.stereotype.Service;

import br.com.jhenriquedsm.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;

@Service
public class VerifyIfHasCertificationUseCase {
    
    public boolean execute(VerifyHasCertificationDTO dto){
        if(dto.getEmail().equals("jhsilvamata@gmail.com") && dto.getTechnology().equals("JAVA")){
            return true;
        }
        return false;
    }
}
