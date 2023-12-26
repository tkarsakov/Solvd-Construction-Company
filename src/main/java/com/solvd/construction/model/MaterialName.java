package com.solvd.construction.model;

public class MaterialName implements Model {
    private Long id;
    private String materialName;

    public MaterialName(Long id, String materialName) {
        this.id = id;
        this.materialName = materialName;
    }

    public MaterialName(String materialName) {
        this.materialName = materialName;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
}
