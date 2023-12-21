package com.solvd.construction.model;

public class Client {
    private final long id;
    private String clientName;
    private String clientEmail;
    private Country country;

    public Client(long id, String clientName, String clientEmail, Country country) {
        this.id = id;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.country = country;
    }

    public long getId() {
        return id;
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
