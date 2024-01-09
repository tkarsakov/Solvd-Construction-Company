package com.solvd.construction.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "client")
@XmlAccessorType(value = XmlAccessType.NONE)
public class Client implements Model {
    private Long id;
    private String clientName;
    private String clientEmail;

    private Country country;
    private Long countryId;

    private Client() {
        this(null, null, null);
    }

    public Client(Long id, String clientName, String clientEmail, Long countryId) {
        this.id = id;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.countryId = countryId;
    }

    public Client(String clientName, String clientEmail, Country country) {
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.country = country;
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

    @XmlElement
    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getClientName() {
        return clientName;
    }

    @XmlElement(required = true)
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    @XmlElement(required = true)
    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public Country getCountry() {
        return country;
    }

    @XmlElement(name = "Country")
    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", countryId=" + countryId +
                "}\n";
    }
}
