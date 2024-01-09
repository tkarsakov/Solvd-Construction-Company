package com.solvd.construction.service;

import com.solvd.construction.model.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierService extends IService<Supplier> {
    Supplier create(Supplier supplier);

    Optional<Supplier> retrieveById(Long id);

    Optional<Supplier> retrieveBySupplierName(String supplierName);

    List<Supplier> retrieveAll();

    void update(Supplier supplier);

    void delete(Long id);
}
