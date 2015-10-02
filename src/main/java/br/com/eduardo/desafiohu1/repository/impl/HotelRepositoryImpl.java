package br.com.eduardo.desafiohu1.repository.impl;

import br.com.eduardo.desafiohu1.domain.Hotel;
import br.com.eduardo.desafiohu1.domain.HotelDate;
import br.com.eduardo.desafiohu1.repository.HotelRepository;
import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Eduardo on 01/10/2015.
 */
@Repository("hotelRepository")
public class HotelRepositoryImpl implements HotelRepository {


    @Override
    public List<Hotel> findHotelByTerm(String term) throws Exception {

        List<Hotel> list = new ArrayList<>();
        URI uriFile = this.getClass().getResource(DB_HOTEL_FILE_PATH).toURI();
        Path path = Paths.get(uriFile);
        Scanner scanner = new Scanner(path, StandardCharsets.UTF_8.name());
        scanner.useDelimiter(System.getProperty("line.separator"));
        while (scanner.hasNext()) {
            String[] record = scanner.next().split(",");
            String id = record[0];
            String cidade = record[1];
            String nome = record[2];
            if (StringUtils.containsIgnoreCase(cidade, term) || StringUtils.containsIgnoreCase(nome, term)) {
                list.add(new Hotel(id, cidade, nome));
            }
        }
        scanner.close();
        return list;
    }

    @Override
    public List<HotelDate> findHotelDateByLocationIdAndPeriod(String locationId, Date beginDate, Date endDate) throws Exception {
        List<HotelDate> list = new ArrayList<>();
        URI uriFile = this.getClass().getResource(DB_HOTEL_DATE_FILE_PATH).toURI();
        Path path = Paths.get(uriFile);
        Scanner scanner = new Scanner(path, StandardCharsets.UTF_8.name());
        scanner.useDelimiter(System.getProperty("line.separator"));
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
        while (scanner.hasNext()) {
            String[] record = scanner.next().split(",");
            String id = record[0];
            LocalDate data = LocalDate.parse(record[1], fmt);
            Boolean disponibilidade = "0".equals(record[2]) ? false : true;
            if (id.equals(locationId) &&
                    ((beginDate == null && endDate == null)
                            || (!data.isBefore(LocalDate.fromDateFields(beginDate)) && !data.isAfter(LocalDate.fromDateFields(endDate).minusDays(1))))
                    ) {
                list.add(new HotelDate(findHotelById(locationId), data.toDate(), disponibilidade));
            }
        }
        scanner.close();
        return list;
    }



    private Hotel findHotelById(String localtionId) throws Exception {

        Hotel hotel = null;
        URI uriFile = this.getClass().getResource(DB_HOTEL_FILE_PATH).toURI();
        Path path = Paths.get(uriFile);
        Scanner scanner = new Scanner(path, StandardCharsets.UTF_8.name());
        scanner.useDelimiter(System.getProperty("line.separator"));
        boolean find = false;
        while (scanner.hasNext() && !find) {
            String[] record = scanner.next().split(",");
            String id = record[0];
            String cidade = record[1];
            String nome = record[2];
            if (StringUtils.equals(id, localtionId)) {
                hotel = new Hotel(id, cidade, nome);
                find = true;
            }
        }
        scanner.close();
        return hotel;
    }

}


