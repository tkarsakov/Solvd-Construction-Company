package com.solvd.construction.model;

import java.math.BigDecimal;

public class ProjectMaterial implements Model {
    private Long id;
    private Long suppliedMaterialId;
    private SuppliedMaterial suppliedMaterial;
    private BigDecimal materialAmount;
    private Long projectId;
    private String measure;

    public ProjectMaterial(Long id, Long suppliedMaterialId, BigDecimal materialAmount, Long projectId, String measure) {
        this.id = id;
        this.suppliedMaterialId = suppliedMaterialId;
        this.materialAmount = materialAmount;
        this.projectId = projectId;
        this.measure = measure;
    }

    public ProjectMaterial(SuppliedMaterial suppliedMaterial, BigDecimal materialAmount, String measure) {
        this.suppliedMaterial = suppliedMaterial;
        this.materialAmount = materialAmount;
        this.measure = measure;
    }

    public Long getSuppliedMaterialId() {
        return suppliedMaterialId;
    }

    public void setSuppliedMaterialId(Long suppliedMaterialId) {
        this.suppliedMaterialId = suppliedMaterialId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMaterialAmount() {
        return materialAmount;
    }

    public void setMaterialAmount(BigDecimal materialAmount) {
        this.materialAmount = materialAmount;
    }


    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public SuppliedMaterial getSuppliedMaterial() {
        return suppliedMaterial;
    }

    public void setSuppliedMaterial(SuppliedMaterial suppliedMaterial) {
        this.suppliedMaterial = suppliedMaterial;
    }
}
