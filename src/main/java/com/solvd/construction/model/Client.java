package com.solvd.construction.model;

public class Client {
    private long id;
    private String clientName;
    private String clientEmail;
    private Country country;
    private Long countryId;

    public Client(long id, String clientName, String clientEmail, Country country) {
        this.id = id;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.country = country;
    }

    public Client(String clientName, String clientEmail, Long countryId) {
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.countryId = countryId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
