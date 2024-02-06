package br.com.jhenriquedsm.certification_nlw;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/primeiraController")
public class PrimeiraController {
    
    @GetMapping("/retornar")
    public Usuario retornoPrimeiraController(){
        var user = new Usuario("José Henrique", 19);
        return user;
    }

    record Usuario(String nome, int idade){}
}
