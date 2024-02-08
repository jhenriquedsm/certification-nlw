package br.com.jhenriquedsm.certification_nlw.modules.questions.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jhenriquedsm.certification_nlw.modules.questions.entities.QuestionEntity;
import br.com.jhenriquedsm.certification_nlw.modules.questions.repositories.QuestionRepository;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    
    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/technology/{technology}")
    public List<QuestionEntity> findByTechnology(@PathVariable String technology){
        System.out.println("TECH -> " + technology);
        return this.questionRepository.findByTechnology(technology);
    }

}