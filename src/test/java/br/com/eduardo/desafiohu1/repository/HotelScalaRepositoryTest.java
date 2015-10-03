package br.com.eduardo.desafiohu1.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by Eduardo on 03/10/2015.
 */
public class HotelScalaRepositoryTest extends HotelRepositoryTest {

    @Autowired
    @Qualifier("hotelScalaRepository")
    public void setRepository(HotelRepository repository) {
        super.setRepository(repository);
    }
}
