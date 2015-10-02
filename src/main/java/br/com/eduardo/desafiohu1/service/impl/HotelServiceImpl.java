package br.com.eduardo.desafiohu1.service.impl;

import br.com.eduardo.desafiohu1.domain.Hotel;
import br.com.eduardo.desafiohu1.domain.HotelDate;
import br.com.eduardo.desafiohu1.repository.HotelRepository;
import br.com.eduardo.desafiohu1.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Eduardo on 01/10/2015.
 */
@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    @Qualifier("hotelRepository")
    private HotelRepository repository;

    @Override
    public List<Hotel> findHotelByTerm(String term) throws Exception {
        if(StringUtils.isEmpty(term)){
            throw new Exception("O termo deve ser informado!");
        }
        return  repository.findHotelByTerm(term);
    }

    @Override
    public List<HotelDate> findHotelDateByLocationIdAndPeriod(String locationId, Date beginDate, Date endDate, boolean anyDate) throws Exception {
        if(StringUtils.isEmpty(locationId)){
            throw new Exception("A localidade deve ser informada!");
        }else {
            if(!anyDate){
                if(beginDate == null || endDate == null) {
                    throw new Exception("As datas de entrada e saida de ser informada!");
                }
            }
        }
        if(anyDate){
            beginDate = null;
            endDate = null;
        }
        return repository.findHotelDateByLocationIdAndPeriod(locationId, beginDate, endDate);
    }
}
