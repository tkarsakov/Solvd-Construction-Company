package com.solvd.construction.model;

import java.math.BigDecimal;

public class SuppliedMaterial {
    private final int id;
    private MaterialName materialName;
    private Supplier supplier;
    private BigDecimal price;

    public SuppliedMaterial(int id, MaterialName materialName, Supplier supplier, BigDecimal price) {
        this.id = id;
        this.materialName = materialName;
        this.supplier = supplier;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public MaterialName getMaterialName() {
        return materialName;
    }

    public void setMaterialName(MaterialName materialName) {
        this.materialName = materialName;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
