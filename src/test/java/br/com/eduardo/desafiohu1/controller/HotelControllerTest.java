package br.com.eduardo.desafiohu1.controller;

import org.junit.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Eduardo on 01/10/2015.
 */

public class HotelControllerTest extends BaseIntegrationTest {

    private String HOTEL_1_ID = "1";
    private static final String CITY = "Araruama";
    private static final String HOTEL_1_NAME = "Mercatto Casa Hotel";

    @Test
    public void searchRequest() throws Exception {
        getMockMvc().perform(get("/search"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("error", nullValue()))
                .andExpect(model().attribute("search", notNullValue()))
                .andExpect(view().name("search/hotels_search"));
    }

    @Test
    public void searchHotelRequestEmptyTerm() throws Exception {
        getMockMvc().perform(get("/search/hotel?term="))
                .andExpect(status().isOk());
    }


    @Test
    public void searchHotelRequestTerm() throws Exception {
        getMockMvc().perform(get("/search/hotel?term=" + HOTEL_1_NAME))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].city", is(CITY)))
                .andExpect(jsonPath("$[0].name", is(HOTEL_1_NAME)));
    }


    @Test
    public void searchHotelDateRequestFormEmpty() throws Exception {
        getMockMvc().perform(post("/search/hotelDate"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("error", notNullValue()))
                .andExpect(view().name("search/hotels_search"));
    }

    @Test
    public void searchHotelDateRequestFormValid() throws Exception {
         getMockMvc().perform(post("/search/hotelDate")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("locationId", HOTEL_1_ID)
                    .param("anyDate", "true")
                    )
                .andExpect(status().isOk())
                .andExpect(model().attribute("hotelDates", notNullValue()))
                .andExpect(model().attribute("hotelDates", hasSize(30)))
                .andExpect(view().name("search/hotels_search"));
    }


}
