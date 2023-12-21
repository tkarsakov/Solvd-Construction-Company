package com.solvd.construction.model;

public class Country {
    private final long id;
    private String countryName;
    private String postalCode;

    public Country(long id, String countryName, String postalCode) {
        this.id = id;
        this.countryName = countryName;
        this.postalCode = postalCode;
    }

    public long getId() {
        return id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
