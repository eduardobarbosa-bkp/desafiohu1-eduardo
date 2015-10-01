package br.com.eduardo.desafiohu1.repository;

import br.com.eduardo.desafiohu1.domain.Disponibilidade;
import br.com.eduardo.desafiohu1.domain.Hotel;

import java.util.Date;
import java.util.List;

/**
 * Created by Eduardo on 01/10/2015.
 * Repositorio de acesso a base de pesquisa de hoteis e disponibilidades por data
 */
public interface HotelRepository {

    /**
     * Busca de hoteis de acordo com o termo informado, sera pesquisado em cidade ou hotel
     * que contenha o termo no texto
     * @param term string para pesquisa
     * @return lista de hoteis de acordo com o termo pesquisado
     */
    List<Hotel> findHotelByTerm(String term);

    /**
     * Busca a disponibilidade de um hotel de acordo com um periodo de datas, retornando todos as datas do periodo
     *  e quais delas estao disponiveis ou nao.
     *  Se endDate = null sera pesquisado todos os registros maiores que beginDate encontrados
     * @param locationId codigo do hotel
     * @param beginDate data de inicio do periodo
     * @param endDate data de termino do periodo
     * @return lista de disponiblidades do hotel no periodo informado
     */
    List<Disponibilidade> findDisponibilidadeByLocationIdAndPeriod(String locationId, Date beginDate, Date endDate);


}
