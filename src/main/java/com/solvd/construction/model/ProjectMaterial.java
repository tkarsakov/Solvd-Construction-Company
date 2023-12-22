package com.solvd.construction.model;

import java.math.BigDecimal;

public class ProjectMaterial {
    private long id;
    private Long suppliedMaterialId;
    private BigDecimal materialAmount;
    private Long projectId;
    private String measure;

    public ProjectMaterial(Long suppliedMaterialId, BigDecimal materialAmount, Long projectId, String measure) {
        this.suppliedMaterialId = suppliedMaterialId;
        this.materialAmount = materialAmount;
        this.projectId = projectId;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
