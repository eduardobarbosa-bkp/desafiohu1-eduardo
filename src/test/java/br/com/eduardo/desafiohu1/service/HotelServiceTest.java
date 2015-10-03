package br.com.eduardo.desafiohu1.service;

import br.com.eduardo.desafiohu1.repository.HotelRepository;
import br.com.eduardo.desafiohu1.service.impl.HotelServiceImpl;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by Eduardo on 02/10/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class HotelServiceTest {

    private String HOTEL_1_NAME = "Mercatto Casa Hotel";
    private String HOTEL_1_ID = "1";


    @InjectMocks
    private HotelService service = new HotelServiceImpl();

    @Mock
    private HotelRepository repository;


    @Before
    public void setup() throws Exception{
        MockitoAnnotations.initMocks(this);
    }


    @Test(expected = Exception.class)
    public void findHotelByTermEmptyValue() throws Exception {
        service.findHotelByTerm(null);
    }

    @Test
    public void findHotelByTermValid() throws Exception {
        service.findHotelByTerm(HOTEL_1_NAME);
        verify(repository, times(1)).findHotelByTerm(HOTEL_1_NAME);
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
        service.findHotelDateByLocationIdAndPeriod(HOTEL_1_ID, now.toDate(), tomorrow.toDate(), false);
        verify(repository, times(1)).findHotelDateByLocationIdAndPeriod(HOTEL_1_ID, now.toDate(), tomorrow.toDate());
    }

    @Test
    public void findHotelDateByLocationIdAndAnyDate() throws Exception {
        service.findHotelDateByLocationIdAndPeriod(HOTEL_1_ID, null, null, true);
        verify(repository, times(1)).findHotelDateByLocationIdAndPeriod(HOTEL_1_ID, null, null);
    }



}
