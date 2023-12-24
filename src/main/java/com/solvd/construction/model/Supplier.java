package com.solvd.construction.model;

public class Supplier implements Model {
    private Long id;
    private String supplierName;
    private String supplierEmail;
    private Long countryId;

    public Supplier(long id, String supplierName, String supplierEmail, Long countryId) {
        this.id = id;
        this.supplierName = supplierName;
        this.supplierEmail = supplierEmail;
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

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }
}
