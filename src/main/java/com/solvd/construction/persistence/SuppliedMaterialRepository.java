package com.solvd.construction.persistence;

import com.solvd.construction.model.SuppliedMaterial;

import java.util.List;
import java.util.Optional;

public interface SuppliedMaterialRepository {
    void create(SuppliedMaterial suppliedMaterial);

    Optional<SuppliedMaterial> findById(Long id);

    List<SuppliedMaterial> findAll();

    void update(SuppliedMaterial suppliedMaterial);

    void deleteById(Long id);
}
