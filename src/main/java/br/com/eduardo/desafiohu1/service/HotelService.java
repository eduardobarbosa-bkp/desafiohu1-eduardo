package br.com.eduardo.desafiohu1.service;

import br.com.eduardo.desafiohu1.domain.HotelDate;
import br.com.eduardo.desafiohu1.domain.Hotel;

import java.util.Date;
import java.util.List;

/**
 * Created by Eduardo on 01/10/2015.
 * Servico utilizado para pesquisa de hoteis e disponibilidades por data
 */
public interface HotelService {

    /**
     * Busca de hoteis de acordo com o termo informado, sera pesquisado em cidade ou hotel
     * que contenha o termo no texto
     * @param term string para pesquisa
     * @return lista de hoteis de acordo com o termo pesquisado
     */
    List<Hotel> findHotelByTerm(String term) throws Exception;

    /**
     * Busca a disponibilidade de um hotel de acordo com um periodo de datas, retornando todos as datas do periodo
     *  e quais delas estao disponiveis ou nao.
     *  Se anyDate = true o periodo de datas sera ignorado e sera retornado todos os registros encontrados para o hotel
     *  apartir da data atual
     * @param locationId codigo do hotel
     * @param beginDate data de inicio do periodo
     * @param endDate data de termino do periodo
     * @param anyDate flag para ignorar o periodo informado
     * @return lista de disponiblidades do hotel no periodo informado
     */
    List<HotelDate> findHotelDateByLocationIdAndPeriod(String locationId, Date beginDate, Date endDate, boolean anyDate) throws Exception;

}
