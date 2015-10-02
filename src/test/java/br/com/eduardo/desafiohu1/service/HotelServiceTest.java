package br.com.eduardo.desafiohu1.service;

import br.com.eduardo.desafiohu1.domain.Hotel;
import br.com.eduardo.desafiohu1.domain.HotelDate;
import br.com.eduardo.desafiohu1.repository.HotelRepository;
import br.com.eduardo.desafiohu1.service.impl.HotelServiceImpl;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Eduardo on 02/10/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class HotelServiceTest {

    private static final String INVALID_TERM = "xxxxxxx";
    private String HOTEL_1_NOME = "Mercatto Casa Hotel";
    private String CIDADE = "Araruama";
    private String HOTEL_1_ID = "1";
    private Hotel HOTEL_1 = new Hotel(HOTEL_1_ID, CIDADE, HOTEL_1_NOME);
    private Hotel HOTEL_2 = new Hotel("2", CIDADE, "Boulevard Higienopolis Residence Hotel");
    private HotelDate HOTEL_DATE_1 = new HotelDate(HOTEL_1, LocalDate.now().toDate(), Boolean.TRUE);
    private HotelDate HOTEL_DATE_2 = new HotelDate(HOTEL_1, LocalDate.now().plusDays(1).toDate(), Boolean.FALSE);


    @InjectMocks
    private HotelService service = new HotelServiceImpl();

    @Mock
    private HotelRepository repository;


    @Before
    public void setup() throws Exception{
        MockitoAnnotations.initMocks(this);
        Mockito.when(repository.findHotelByTerm(INVALID_TERM)).thenReturn(Collections.EMPTY_LIST);
        Mockito.when(repository.findHotelByTerm(HOTEL_1_NOME)).thenReturn(Arrays.asList(HOTEL_1));
        Mockito.when(repository.findHotelByTerm(CIDADE)).thenReturn(Arrays.asList(HOTEL_1, HOTEL_2));

        LocalDate now = LocalDate.now();
        LocalDate tomorrow = now.plusDays(1);
        Mockito.when(repository.findHotelDateByLocationIdAndPeriod(HOTEL_1_ID, now.toDate(), tomorrow.toDate())).thenReturn(Arrays.asList(HOTEL_DATE_1));
        Mockito.when(repository.findHotelDateByLocationIdAndPeriod(HOTEL_1_ID, null, null)).thenReturn(Arrays.asList(HOTEL_DATE_1, HOTEL_DATE_2));
        Mockito.when(repository.findHotelDateByLocationIdAndPeriod(HOTEL_1_ID, now.plusDays(2).toDate(), now.plusDays(3).toDate())).thenReturn(Collections.EMPTY_LIST);
    }


    @Test(expected = Exception.class)
    public void findHotelByTermEmptyValue() throws Exception {
        service.findHotelByTerm(null);
    }

    @Test
    public void findHotelByTermNotFound() throws Exception {
        List<Hotel> list = service.findHotelByTerm(INVALID_TERM);
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
    public void findHotelDateByLocationIdAndPeriodLocationIdNull() throws Exception {
        service.findHotelDateByLocationIdAndPeriod(null, null, null, false);
    }

    @Test(expected = Exception.class)
    public void findHotelDateByLocationIdAndPeriodPeriodNull() throws Exception {
        service.findHotelDateByLocationIdAndPeriod(HOTEL_1_ID, null, null, false);
    }

    @Test
    public void findHotelDateByLocationIdAndPeriodValid() throws Exception {
        LocalDate now = LocalDate.now();
        LocalDate tomorrow = now.plusDays(1);
        List<HotelDate> list = service.findHotelDateByLocationIdAndPeriod(HOTEL_1_ID, now.toDate(), tomorrow.toDate(), false);
        assertNotNull(list);
        assertTrue(list.size() == 1);
        HotelDate hotelDate = list.get(0);
        assertSame(hotelDate.getHotel().getId(), HOTEL_1_ID);
    }

    @Test
    public void findHotelDateByLocationIdAndPeriodAnyDate() throws Exception {
        List<HotelDate> list = service.findHotelDateByLocationIdAndPeriod(HOTEL_1_ID, null, null, true);
        assertNotNull(list);
        assertTrue(list.size() == 2);
    }

    @Test
    public void findHotelDateByLocationIdAndPeriodDateNotExist() throws Exception {
        LocalDate now = LocalDate.now();
        List<HotelDate> list = service.findHotelDateByLocationIdAndPeriod(HOTEL_1_ID, now.plusDays(2).toDate(), now.plusDays(3).toDate(), false);
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

}
