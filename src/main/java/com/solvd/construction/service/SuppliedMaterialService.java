package com.solvd.construction.service;

import com.solvd.construction.model.SuppliedMaterial;

import java.util.List;
import java.util.Optional;

public interface SuppliedMaterialService extends IService<SuppliedMaterial> {
    SuppliedMaterial create(SuppliedMaterial suppliedMaterial);

    List<SuppliedMaterial> retrieveAll();

    Optional<SuppliedMaterial> retrieveById(Long id);
}
