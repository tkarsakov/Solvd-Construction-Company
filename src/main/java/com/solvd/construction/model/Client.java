package com.solvd.construction.model;

public class Client implements Model {
    private Long id;
    private String clientName;
    private String clientEmail;
    private Country country;
    private Long countryId;

    public Client(Long id, String clientName, String clientEmail, Long countryId) {
        this.id = id;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.countryId = countryId;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
