package br.com.eduardo.desafiohu1.domain;

import java.io.Serializable;

/**
 * Created by Eduardo on 30/09/2015
 */
public class Hotel implements Serializable {

    private static final long serialVersionUID = 262841061338493222L;

    private String id;

    private String city;

    private String name;

    public Hotel(String id, String city, String name) {
        this.id = id;
        this.city = city;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel)) return false;

        Hotel hotel = (Hotel) o;

        return id.equals(hotel.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
