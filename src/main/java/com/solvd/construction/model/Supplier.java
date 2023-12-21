package com.solvd.construction.model;

public class Supplier {
    private final long id;
    private String supplierName;
    private String supplierEmail;
    private Country country;

    public Supplier(long id, String supplierName, String supplierEmail, Country country) {
        this.id = id;
        this.supplierName = supplierName;
        this.supplierEmail = supplierEmail;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
