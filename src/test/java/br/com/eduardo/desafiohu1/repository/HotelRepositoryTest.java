package br.com.eduardo.desafiohu1.repository;

import br.com.eduardo.desafiohu1.Application;
import br.com.eduardo.desafiohu1.domain.Hotel;
import br.com.eduardo.desafiohu1.domain.HotelDate;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: eduardo.barbosa
 * @since: 02/10/2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class HotelRepositoryTest {

    private static final String INVALID_TERM = "xxxxxxx";
    private static final String CIDADE = "Araruama";
    private static final String HOTEL_1_NOME = "Mercatto Casa Hotel";
    private static final String HOTEL_TERM = "Pousada";
    private static final String HOTEL_1_ID = "1";
    private static final Date DATE_NOT_AVAILABLE = LocalDate.parse("2015-05-01").toDate();
    private static final Date DATE_AVAILABLE = LocalDate.parse("2015-05-04").toDate();

    @Autowired
    @Qualifier("hotelRepository")
    private HotelRepository repository;

    @Test
    public void findHotelByTermNotFound() throws Exception {
        List<Hotel> list = repository.findHotelByTerm(INVALID_TERM);
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    public void findHotelByTermExact() throws Exception {
        List<Hotel> list = repository.findHotelByTerm(HOTEL_1_NOME);
        assertNotNull(list);
        assertTrue(list.size() == 1);
        Hotel hotel = list.get(0);
        assertEquals(hotel.getNome(), HOTEL_1_NOME);
    }

    @Test
    public void findHotelByTermPartialCidade() throws Exception {
        List<Hotel> list = repository.findHotelByTerm(CIDADE);
        assertNotNull(list);
        assertTrue(list.size() == 2);
    }

    @Test
    public void findHotelByTermPartialHotel() throws Exception {
        List<Hotel> list = repository.findHotelByTerm(HOTEL_TERM);
        assertNotNull(list);
        assertTrue(list.size() == 2);
    }

    @Test
    public void findHotelDateByLocationIdAndPeriodValidNotAvailable() throws Exception {
        List<HotelDate> list = repository.findHotelDateByLocationIdAndPeriod(HOTEL_1_ID, DATE_NOT_AVAILABLE, LocalDate.fromDateFields(DATE_NOT_AVAILABLE).plusDays(1).toDate());
        assertNotNull(list);
        assertTrue(list.size() == 1);
        HotelDate hotelDate = list.get(0);
        assertEquals(hotelDate.getHotel().getId(), HOTEL_1_ID);
        assertEquals(hotelDate.getDate(), DATE_NOT_AVAILABLE);
        assertEquals(hotelDate.getAvailable(), Boolean.FALSE);
    }

    @Test
    public void findHotelDateByLocationIdAndPeriodValidAvailable() throws Exception {
        List<HotelDate> list = repository.findHotelDateByLocationIdAndPeriod(HOTEL_1_ID, DATE_AVAILABLE, LocalDate.fromDateFields(DATE_AVAILABLE).plusDays(1).toDate());
        assertNotNull(list);
        assertTrue(list.size() == 1);
        HotelDate hotelDate = list.get(0);
        assertEquals(hotelDate.getHotel().getId(), HOTEL_1_ID);
        assertEquals(hotelDate.getDate(), DATE_AVAILABLE);
        assertEquals(hotelDate.getAvailable(), Boolean.TRUE);
    }

    @Test
    public void findHotelDateByLocationIdAndPeriodValidAllDate() throws Exception {
        List<HotelDate> list = repository.findHotelDateByLocationIdAndPeriod(HOTEL_1_ID, null, null);
        assertNotNull(list);
        assertTrue(list.size() == 30);
    }

}
