package com.solvd.construction.model;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "country")
@XmlType(propOrder = {"countryName", "postalCode"}, name = "CountryType")
@XmlAccessorType(value = XmlAccessType.NONE)
public class Country implements Model {
    private Long id;
    @XmlElement(required = true)
    private String countryName;
    @XmlElement(required = true)
    private Long postalCode;

    private Country() {
        this(null, null);
    }

    public Country(Long id, String countryName, Long postalCode) {
        this.id = id;
        this.countryName = countryName;
        this.postalCode = postalCode;
    }

    public Country(String countryName, Long postalCode) {
        this.countryName = countryName;
        this.postalCode = postalCode;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }


    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Long getPostalCode() {
        return postalCode;
    }


    public void setPostalCode(Long postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", countryName='" + countryName + '\'' +
                ", postalCode=" + postalCode +
                "}\n";
    }
}
