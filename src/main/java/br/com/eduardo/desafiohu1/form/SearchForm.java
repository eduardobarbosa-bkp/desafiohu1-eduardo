package br.com.eduardo.desafiohu1.form;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Eduardo on 01/10/2015.
 */
public class SearchForm {

    private String locationId;

    private String location;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date beginDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date endDate;

    private Boolean anyDate;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getAnyDate() {
        return anyDate;
    }

    public void setAnyDate(Boolean anyDate) {
        this.anyDate = anyDate;
    }
}
