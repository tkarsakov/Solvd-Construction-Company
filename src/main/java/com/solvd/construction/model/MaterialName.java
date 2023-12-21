package com.solvd.construction.model;

public class MaterialName {
    private final long id;
    private String materialName;

    public MaterialName(long id, String materialName) {
        this.id = id;
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
