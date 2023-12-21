package com.solvd.construction.model;

public class MaterialName {
    private final int id;
    private String materialName;

    public MaterialName(int id, String materialName) {
        this.id = id;
        this.materialName = materialName;
    }

    public int getId() {
        return id;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
}
