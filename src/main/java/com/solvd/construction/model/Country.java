package com.solvd.construction.model;

public class Country implements Model {
    private Long id;
    private String countryName;
    private Long postalCode;

    public Country(Long id, String countryName, Long postalCode) {
        this.id = id;
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
}
