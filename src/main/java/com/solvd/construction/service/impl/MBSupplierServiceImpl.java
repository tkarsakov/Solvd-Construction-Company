package com.solvd.construction.service.impl;

import com.solvd.construction.model.Supplier;
import com.solvd.construction.service.SupplierService;
import com.solvd.construction.persistence.mappers.SupplierMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.Optional;
import java.util.function.Consumer;

public class MBSupplierServiceImpl implements SupplierService {
    private final SupplierMapper supplierMapper;
    private final MBCountryServiceImpl countryService;

    public MBSupplierServiceImpl(SqlSession session) {
        this.supplierMapper = session.getMapper(SupplierMapper.class);
        this.countryService = new MBCountryServiceImpl(session);
    }

    @Override
    public Supplier create(Supplier supplier) {
        supplier.setId(null);
        if (supplier.getCountryId() == null || supplier.getCountry().getId() == null) {
            countryService.create(supplier.getCountry());
        }
        return supplierMapper.create(supplier);
    }

    @Override
    public Optional<Supplier> retrieveById(Long id) {
        Optional<Supplier> optionalSupplier = Optional.of(supplierMapper.retrieveById(id));
        optionalSupplier.ifPresent(setFields());
        return optionalSupplier;
    }

    @Override
    public Optional<Supplier> retrieveBySupplierName(String supplierName) {
        Optional<Supplier> optionalSupplier = Optional.of(supplierMapper.retrieveBySupplierName(supplierName));
        optionalSupplier.ifPresent(setFields());
        return optionalSupplier;
    }

    private Consumer<Supplier> setFields() {
        return supplier -> supplier.setCountry(
                countryService.retrieveById(supplier.getCountryId()).orElse(null)
        );
    }
}
