package br.com.eduardo.desafiohu1.controller;

import br.com.eduardo.desafiohu1.domain.Hotel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * @author: eduardo.barbosa
 * @since: 30/09/2015
 */
@Controller
public class HotelController {

    @RequestMapping(value = "/hotels", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public List<Hotel> getHotels(@RequestParam("term") String term) {
        return Arrays.asList(new Hotel("1","Araruama","Mercatto Casa Hotel"), new Hotel("2","Macae","Boulevard Higienopolis Residence Hotel"));
    }

}
