package com.solvd.construction.persistence;

import com.solvd.construction.model.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository {
    void create(Supplier supplier);

    Optional<Supplier> findById(Long id);

    List<Supplier> findAll();

    void update(Supplier supplier);

    void deleteById(Long id);

    Optional<Supplier> findBySupplierName(String supplierName);
}
