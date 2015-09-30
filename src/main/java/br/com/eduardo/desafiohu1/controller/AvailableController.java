package br.com.eduardo.desafiohu1.controller;

import br.com.eduardo.desafiohu1.domain.Disponibilidade;
import br.com.eduardo.desafiohu1.domain.Hotel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.Date;

/**
 * @author: eduardo.barbosa
 * @since: 29/09/2015
 */
@Controller
public class AvailableController {

    @RequestMapping(value = "/availables", method = RequestMethod.POST)
    public String getAvailables(Model model) {
        model.addAttribute("availables", Arrays.asList(new Disponibilidade(new Hotel("1", "Araruama", "Mercatto Casa Hotel"), new Date(), Boolean.TRUE)));
        return "index";
    }

}
