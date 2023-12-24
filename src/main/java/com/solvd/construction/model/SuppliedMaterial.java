package com.solvd.construction.model;

import java.math.BigDecimal;

public class SuppliedMaterial implements Model {
    private Long id;
    private Long materialNameId;
    private Long supplierId;
    private BigDecimal price;

    public SuppliedMaterial(Long id, Long materialNameId, Long supplierId, BigDecimal price) {
        this.id = id;
        this.materialNameId = materialNameId;
        this.supplierId = supplierId;
        this.price = price;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getMaterialNameId() {
        return materialNameId;
    }

    public void setMaterialNameId(Long materialNameId) {
        this.materialNameId = materialNameId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
