package com.solvd.construction.service;

import com.solvd.construction.model.SuppliedMaterial;

import java.util.List;

public interface SuppliedMaterialService {
    SuppliedMaterial create(SuppliedMaterial suppliedMaterial);

    List<SuppliedMaterial> retrieveAll();
}
