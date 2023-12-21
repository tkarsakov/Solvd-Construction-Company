package com.solvd.construction.model;

import java.math.BigDecimal;

public class ProjectMaterial {
    private final int id;
    private SuppliedMaterial suppliedMaterial;
    private BigDecimal materialAmount;
    private Project project;
    private String measure;

    public ProjectMaterial(int id, SuppliedMaterial suppliedMaterial, BigDecimal materialAmount,
                           Project project, String measure) {
        this.id = id;
        this.suppliedMaterial = suppliedMaterial;
        this.materialAmount = materialAmount;
        this.project = project;
        this.measure = measure;
    }

    public int getId() {
        return id;
    }

    public SuppliedMaterial getSuppliedMaterial() {
        return suppliedMaterial;
    }

    public void setSuppliedMaterial(SuppliedMaterial suppliedMaterial) {
        this.suppliedMaterial = suppliedMaterial;
    }

    public BigDecimal getMaterialAmount() {
        return materialAmount;
    }

    public void setMaterialAmount(BigDecimal materialAmount) {
        this.materialAmount = materialAmount;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }
}
