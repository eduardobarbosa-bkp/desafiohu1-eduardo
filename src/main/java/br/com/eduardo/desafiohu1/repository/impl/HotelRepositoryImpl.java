package br.com.eduardo.desafiohu1.repository.impl;

import br.com.eduardo.desafiohu1.domain.Disponibilidade;
import br.com.eduardo.desafiohu1.domain.Hotel;
import br.com.eduardo.desafiohu1.repository.HotelRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Eduardo on 01/10/2015.
 */
@Repository
public class HotelRepositoryImpl implements HotelRepository {

    @Override
    public List<Hotel> findHotelByTerm(String term) {
        return  Arrays.asList(new Hotel("1", "Araruama", "Mercatto Casa Hotel"), new Hotel("2", "Macae", "Boulevard Higienopolis Residence Hotel"));
    }

    @Override
    public List<Disponibilidade> findDisponibilidadeByLocationIdAndPeriod(String locationId, Date beginDate, Date endDate) {
        return Arrays.asList(new Disponibilidade(new Hotel("1", "Araruama", "Mercatto Casa Hotel"), new Date(), Boolean.TRUE));
    }
}
