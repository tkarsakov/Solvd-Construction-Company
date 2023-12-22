package com.solvd.construction.model;

public class Country {
    private String countryName;
    private Long postalCode;

    public Country(String countryName, Long postalCode) {
        this.countryName = countryName;
        this.postalCode = postalCode;
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
}
