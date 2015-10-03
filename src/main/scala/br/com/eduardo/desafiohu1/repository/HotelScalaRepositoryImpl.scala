package br.com.eduardo.desafiohu1.repository

import java.util
import java.util.Date

import br.com.eduardo.desafiohu1.domain.{HotelDate, Hotel}
import org.joda.time.LocalDate
import org.joda.time.format.{DateTimeFormat}
import org.springframework.stereotype.Repository
import scala.collection.JavaConversions._

import scala.io.Source

/**
 * Created by Eduardo on 03/10/2015.
 */
@Repository("hotelScalaRepository")
class HotelScalaRepositoryImpl extends HotelRepository{

  override def findHotelByTerm(term: String): util.List[Hotel] = {
    val result =  Source.fromInputStream(getClass.getResourceAsStream(HotelRepository.DB_HOTEL_FILE_PATH)).getLines
    .filter(line => {
         val parts = line.split(",")
         val city: String = parts(1)
         val name: String = parts(2)
          city.toUpperCase.contains(term.toUpperCase) || name.toUpperCase.contains(term.toUpperCase)
        }
    ).toList
    .map(line => {
       val parts = line.split(",")
       val id: String = parts(0)
       val city: String = parts(1)
       val name: String = parts(2)
       new Hotel(id, city, name)
      })
    result
  }

  override def findHotelDateByLocationIdAndPeriod(locationId: String, beginDate: Date, endDate: Date): util.List[HotelDate] = {
    val fmt = DateTimeFormat.forPattern("dd/MM/yyyy")
    val result =  Source.fromInputStream(getClass.getResourceAsStream(HotelRepository.DB_HOTEL_DATE_FILE_PATH)).getLines
      .filter(line => {
        val parts = line.split(",")
        val id: String = parts(0)
        val date: LocalDate = LocalDate.parse(parts(1), fmt)
        (id == locationId) &&
           (
            (beginDate == null && endDate == null)
              || (!date.isBefore(LocalDate.fromDateFields(beginDate)) && !date.isAfter(LocalDate.fromDateFields(endDate).minusDays(1)))
            )
      }
      ).toList
      .map(line => {
        val parts = line.split(",")
        val id: String = parts(0)
        val date: LocalDate = LocalDate.parse(parts(1), fmt)
        val available: Boolean = !("0" == parts(2))
        new HotelDate(findHotelById(id), date.toDate, available)
      })
    result
  }

  private def findHotelById(locationId:String) : Hotel = {
    val result =  Source.fromInputStream(getClass.getResourceAsStream(HotelRepository.DB_HOTEL_FILE_PATH)).getLines
      .filter(line => {
        val parts = line.split(",")
        val id: String = parts(0)
        id.equals(locationId)
      }
      ).toList
      .map(line => {
        val parts = line.split(",")
        val id: String = parts(0)
        val city: String = parts(1)
        val name: String = parts(2)
        new Hotel(id, city, name)
      })
    result(0)
  }
}