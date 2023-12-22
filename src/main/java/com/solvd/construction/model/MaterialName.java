package com.solvd.construction.model;

public class MaterialName {
    private long id;
    private String materialName;

    public MaterialName(String materialName) {
        this.materialName = materialName;
    }

    public long getId() {
        return id;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
}
