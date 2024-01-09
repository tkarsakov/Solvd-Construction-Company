package com.solvd.construction.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.NONE)
public class Supplier implements Model {
    private Long id;
    private String supplierName;
    private String supplierEmail;
    private Long countryId;
    private Country country;

    private Supplier() {
        this(null, null, null);
    }

    public Supplier(long id, String supplierName, String supplierEmail, Long countryId) {
        this.id = id;
        this.supplierName = supplierName;
        this.supplierEmail = supplierEmail;
        this.countryId = countryId;
    }

    public Supplier(String supplierName, String supplierEmail, Country country) {
        this.supplierName = supplierName;
        this.supplierEmail = supplierEmail;
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

    public String getSupplierName() {
        return supplierName;
    }

    @XmlElement
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    @XmlElement
    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public Long getCountryId() {
        return countryId;
    }

    @XmlElement
    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Country getCountry() {
        return country;
    }

    @XmlElement(name = "country")
    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", supplierName='" + supplierName + '\'' +
                ", supplierEmail='" + supplierEmail + '\'' +
                ", countryId=" + countryId +
                "\n";
    }
}
