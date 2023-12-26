package com.solvd.construction.service;

import com.solvd.construction.model.Supplier;

import java.util.Optional;

public interface SupplierService {
    Supplier create(Supplier supplier);

    Optional<Supplier> retrieveById(Long id);
}
