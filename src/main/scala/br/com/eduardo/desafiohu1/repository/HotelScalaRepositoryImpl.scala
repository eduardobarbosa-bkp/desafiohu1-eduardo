package br.com.eduardo.desafiohu1.repository

import java.util
import java.util.Date

import br.com.eduardo.desafiohu1.domain.{HotelDate, Hotel}
import org.joda.time.LocalDate
import org.joda.time.format.{DateTimeFormat}
import org.springframework.stereotype.Repository
import scala.collection.JavaConversions._

import scala.io.Source

@Repository("hotelScalaRepository")
class HotelScalaRepositoryImpl extends HotelRepository{

  override def findHotelByTerm(term: String): util.List[Hotel] = {
    val result =  Source.fromInputStream(getClass.getResourceAsStream(HotelRepository.DB_HOTEL_FILE_PATH)).getLines
    .filter(line => {
         val parts = line.split(",")
         val cidade: String = parts(1)
         val nome: String = parts(2)
         cidade.toUpperCase.contains(term.toUpperCase) || nome.toUpperCase.contains(term.toUpperCase)
        }
    ).toList
    .map(line => {
       val parts = line.split(",")
       val id: String = parts(0)
       val cidade: String = parts(1)
       val nome: String = parts(2)
       new Hotel(id, cidade, nome)
      })
    result
  }

  override def findHotelDateByLocationIdAndPeriod(locationId: String, beginDate: Date, endDate: Date): util.List[HotelDate] = {
    val fmt = DateTimeFormat.forPattern("dd/MM/yyyy")
    val result =  Source.fromInputStream(getClass.getResourceAsStream(HotelRepository.DB_HOTEL_DATE_FILE_PATH)).getLines
      .filter(line => {
        val parts = line.split(",")
        val id: String = parts(0)
        val data: LocalDate = LocalDate.parse(parts(1), fmt)
        val disponibilidade: Boolean = if (("0" == parts(2))) false else true
        (id == locationId) &&
           (
            (beginDate == null && endDate == null)
              || (!data.isBefore(LocalDate.fromDateFields(beginDate)) && !data.isAfter(LocalDate.fromDateFields(endDate).minusDays(1)))
            )
      }
      ).toList
      .map(line => {
        val parts = line.split(",")
        val id: String = parts(0)
        val data: LocalDate = LocalDate.parse(parts(1), fmt)
        val disponibilidade: Boolean = if (("0" == parts(2))) false else true
        new HotelDate(findHotelById(id), data.toDate, disponibilidade)
      })
    result
  }

  private def findHotelById(localtionId:String) : Hotel = {
    val result =  Source.fromInputStream(getClass.getResourceAsStream(HotelRepository.DB_HOTEL_FILE_PATH)).getLines
      .filter(line => {
        val parts = line.split(",")
        val id: String = parts(0)
        id.equals(localtionId)
      }
      ).toList
      .map(line => {
        val parts = line.split(",")
        val id: String = parts(0)
        val cidade: String = parts(1)
        val nome: String = parts(2)
        new Hotel(id, cidade, nome)
      })
    result(0)
  }
}