package com.solvd.construction.service.impl.jdbc;

import com.solvd.construction.model.Supplier;
import com.solvd.construction.persistence.SupplierRepository;
import com.solvd.construction.persistence.impl.SupplierRepositoryImplDAO;
import com.solvd.construction.service.CountryService;
import com.solvd.construction.service.SupplierService;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

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
        if (supplier.getCountryId() != null) {
            supplier.setCountry(countryService.retrieveById(supplier.getCountryId()).orElse(null));
        } else {
            countryService.create(supplier.getCountry());
            supplier.setCountryId(supplier.getCountry().getId());
        }
        supplierRepository.create(supplier);
        return supplier;
    }

    @Override
    public Optional<Supplier> retrieveById(Long id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        optionalSupplier.ifPresent(setFields());
        return optionalSupplier;
    }

    @Override
    public Optional<Supplier> retrieveBySupplierName(String supplierName) {
        Optional<Supplier> optionalSupplier = supplierRepository.findBySupplierName(supplierName);
        optionalSupplier.ifPresent(setFields());
        return optionalSupplier;
    }

    @Override
    public List<Supplier> retrieveAll() {
        List<Supplier> suppliers = supplierRepository.findAll();
        suppliers.forEach(setFields());
        return suppliers;
    }

    @Override
    public void update(Supplier supplier) {
        supplierRepository.update(supplier);
    }

    @Override
    public void delete(Long id) {
        supplierRepository.deleteById(id);
    }

    private Consumer<Supplier> setFields() {
        return supplier -> supplier.setCountry(
                countryService.retrieveById(supplier.getCountryId()).orElse(null)
        );
    }
}
