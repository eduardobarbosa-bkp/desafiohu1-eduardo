package br.com.eduardo.desafiohu1.controller;

import br.com.eduardo.desafiohu1.domain.Disponibilidade;
import br.com.eduardo.desafiohu1.domain.Hotel;
import br.com.eduardo.desafiohu1.form.SearchForm;
import br.com.eduardo.desafiohu1.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author: eduardo.barbosa
 * @since: 30/09/2015
 */
@Controller
public class HotelController {

    @Autowired
    private HotelService service;

    /**
     * Setup no formulario de busca e inicializa os atributos
     *
     * @param model objeto que encapusula os parametros do request
     * @return pagina de busca de hoteis
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String setup(Model model) {
        model.addAttribute("search", new SearchForm());
        model.addAttribute("exeption", null);
        return "search/hotels_search";
    }

    /**
     * Busca de hoteis de acordo com um termo, que poder ser o nome da cidade ou hotel
     *
     * @param term  string contendo o termo de busca
     * @param model objeto que encapusula os parametros do request
     * @return json contendo uma lista de hoteis
     */
    @RequestMapping(value = "/search/hotel", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Hotel> getHotel(@RequestParam("term") String term, Model model) {
        List<Hotel> hotelByTerm = null;
        try {
            hotelByTerm = service.findHotelByTerm(term);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return hotelByTerm;
    }

    /**
     * Realiza a pesquisa de disponibilidades do hotel solicitado adicionando o resultado no request
     *
     * @param search object que encapsula os atributos do formulario (locationId, location, beginDate, endDate e anyDate)
     * @param model  objeto que encapusula os parametros do request
     * @return pagina de busca de hoteis
     */
    @RequestMapping(value = "/search/available", method = RequestMethod.POST)
    public String getAvailable(@ModelAttribute("search") SearchForm search, Model model) {
        model.addAttribute("search", search);
        List<Disponibilidade> disponibilidades = null;
        try {
            disponibilidades = service.findDisponibilidadeByLocationIdAndPeriod(search.getLocationId(), search.getBeginDate(), search.getEndDate(), search.isAnyDate());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("availables", disponibilidades);
        return "search/hotels_search";
    }

}
