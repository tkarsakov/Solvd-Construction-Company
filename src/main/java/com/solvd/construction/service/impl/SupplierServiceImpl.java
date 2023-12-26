package com.solvd.construction.service.impl;

import com.solvd.construction.model.Supplier;
import com.solvd.construction.persistence.SupplierRepository;
import com.solvd.construction.persistence.impl.SupplierRepositoryImplDAO;
import com.solvd.construction.service.CountryService;
import com.solvd.construction.service.SupplierService;

import java.util.Optional;

public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final CountryService countryService;

    public SupplierServiceImpl() {
        this.supplierRepository = new SupplierRepositoryImplDAO();
        this.countryService = new CountryServiceImpl();
    }

    @Override
    public Supplier create(Supplier supplier) {
        supplier.setId(null);
        if (countryService.retrieveByCountryName(supplier.getCountry().getCountryName()).isEmpty()) {
            countryService.create(supplier.getCountry());
        }
        supplierRepository.create(supplier);
        return supplier;
    }

    @Override
    public Optional<Supplier> retrieveById(Long id) {
        return supplierRepository.findById(id);
    }
}
