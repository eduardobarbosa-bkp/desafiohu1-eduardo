package br.com.eduardo.desafiohu1.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Created by Eduardo on 01/10/2015.
 */

public class HotelControllerTest extends BaseIntegrationTest {


    @Test
    public void checkRequestSearch() throws Exception {
        getMockMvc().perform(MockMvcRequestBuilders.get("/search"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void checkRequestSearchHotel() throws Exception {
        getMockMvc().perform(MockMvcRequestBuilders.get("/search/hotel?term="))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void checkRequestSearchHotelDate() throws Exception {
        getMockMvc().perform(MockMvcRequestBuilders.post("/search/hotelDate"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
