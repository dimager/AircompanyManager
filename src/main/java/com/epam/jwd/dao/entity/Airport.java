package com.epam.jwd.dao.entity;

import java.util.Objects;

public class Airport extends Entity{
    private int id;
    private String name;
    private String country;
    private String city;
    private String IATACode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIATACode() {
        return IATACode;
    }

    public void setIATACode(String IATACode) {
        this.IATACode = IATACode;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return id == airport.id && name.equals(airport.name) && country.equals(airport.country) && city.equals(airport.city) && IATACode.equals(airport.IATACode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country, city, IATACode);
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", IATACode='" + IATACode + '\'' +
                '}';
    }
}
