package br.com.eduardo.desafiohu1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Eduardo on 29/09/2015
 */
@Controller
public class IndexController {

    /**
     * Redireciona para a home da aplicacao
     * @return pagina inicial
     */
    @RequestMapping("/")
    public String home() {
        return "index";
    }
}
