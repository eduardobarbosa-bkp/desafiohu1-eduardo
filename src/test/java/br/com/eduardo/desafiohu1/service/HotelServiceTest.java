package br.com.eduardo.desafiohu1.service;

import br.com.eduardo.desafiohu1.Application;
import br.com.eduardo.desafiohu1.domain.Disponibilidade;
import br.com.eduardo.desafiohu1.domain.Hotel;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Eduardo on 02/10/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class HotelServiceTest {

    private String HOTEL_1_NOME = "Mercatto Casa Hotel";
    private String CIDADE = "Araruama";
    private String HOTEL_1_ID = "1";
    private Hotel HOTEL_1 = new Hotel(HOTEL_1_ID, CIDADE, HOTEL_1_NOME);
    private Hotel HOTEL_2 = new Hotel("2", CIDADE, "Boulevard Higienopolis Residence Hotel");
    private Disponibilidade DISP_1 = new Disponibilidade(HOTEL_1, LocalDate.now().toDate(), Boolean.TRUE);
    private Disponibilidade DISP_2 = new Disponibilidade(HOTEL_1, LocalDate.now().plusDays(1).toDate(), Boolean.FALSE);


    @Autowired
    private HotelService service;

    @Test(expected = Exception.class)
    public void findHotelByTermEmptyValue() throws Exception {
        service.findHotelByTerm(null);
    }

    @Test
    public void findHotelByTermNotFound() throws Exception {
        List<Hotel> list = service.findHotelByTerm("xxxxxxx");
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    public void findHotelByTermExact() throws Exception {
        List<Hotel> list = service.findHotelByTerm(HOTEL_1_NOME);
        assertNotNull(list);
        assertTrue(list.size() == 1);
        Hotel hotel = list.get(0);
        assertSame(hotel.getNome(), HOTEL_1_NOME);
    }

    @Test
    public void findHotelByTermPartial() throws Exception {
        List<Hotel> list = service.findHotelByTerm(CIDADE);
        assertNotNull(list);
        assertTrue(list.size() == 2);
    }

    @Test(expected = Exception.class)
    public void findDisponibilidadeByLocationIdAndPeriodLocationIdNull() throws Exception {
        service.findDisponibilidadeByLocationIdAndPeriod(null, null, null, null);
    }

    @Test(expected = Exception.class)
    public void findDisponibilidadeByLocationIdAndPeriodPeriodNull() throws Exception {
        service.findDisponibilidadeByLocationIdAndPeriod(HOTEL_1_ID, null, null, Boolean.FALSE);
    }

    @Test(expected = Exception.class)
    public void findDisponibilidadeByLocationIdAndPeriodBeginDateBeforeToday() throws Exception {
        LocalDate now = LocalDate.now();
        LocalDate yesterday = now.minusDays(1);
        service.findDisponibilidadeByLocationIdAndPeriod(HOTEL_1_ID, yesterday.toDate(), now.toDate(), Boolean.FALSE);
    }

    @Test
    public void findDisponibilidadeByLocationIdAndPeriodValid() throws Exception {
        LocalDate now = LocalDate.now();
        LocalDate tomorrow = now.plusDays(1);
        List<Disponibilidade> list = service.findDisponibilidadeByLocationIdAndPeriod(HOTEL_1_ID, now.toDate(), tomorrow.toDate(), Boolean.FALSE);
        assertNotNull(list);
        assertTrue(list.size() == 1);
        Disponibilidade disponibilidade = list.get(0);
        assertSame(disponibilidade.getHotel().getId(), HOTEL_1_ID);
    }

    @Test
    public void findDisponibilidadeByLocationIdAndPeriodAnyDate() throws Exception {
        List<Disponibilidade> list = service.findDisponibilidadeByLocationIdAndPeriod(HOTEL_1_ID, null, null, Boolean.TRUE);
        assertNotNull(list);
        assertTrue(list.size() == 2);
    }

}
