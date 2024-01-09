package com.solvd.construction.service.impl;

import com.solvd.construction.model.Supplier;
import com.solvd.construction.persistence.mappers.SupplierMapper;
import com.solvd.construction.service.CountryService;
import com.solvd.construction.service.SupplierService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class MBSupplierServiceImpl implements SupplierService {
    private final SqlSessionFactory sessionFactory;
    private final CountryService countryService;

    public MBSupplierServiceImpl(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.countryService = new MBCountryServiceImpl(sessionFactory);
    }

    @Override
    public Supplier create(Supplier supplier) {
        try (SqlSession session = sessionFactory.openSession()) {
            SupplierMapper supplierMapper = session.getMapper(SupplierMapper.class);
            supplier.setId(null);
            if (supplier.getCountryId() != null) {
                supplier.setCountry(countryService.retrieveById(supplier.getCountryId()).orElse(null));
            } else {
                countryService.create(supplier.getCountry());
                supplier.setCountryId(supplier.getCountry().getId());
            }
            supplierMapper.create(supplier);
            session.commit();
            return supplier;
        }
    }

    @Override
    public Optional<Supplier> retrieveById(Long id) {
        try (SqlSession session = sessionFactory.openSession()) {
            SupplierMapper supplierMapper = session.getMapper(SupplierMapper.class);
            Optional<Supplier> optionalSupplier = Optional.of(supplierMapper.retrieveById(id));
            optionalSupplier.ifPresent(setFields());
            return optionalSupplier;
        }
    }

    @Override
    public Optional<Supplier> retrieveBySupplierName(String supplierName) {
        try (SqlSession session = sessionFactory.openSession()) {
            SupplierMapper supplierMapper = session.getMapper(SupplierMapper.class);
            Optional<Supplier> optionalSupplier = Optional.of(supplierMapper.retrieveBySupplierName(supplierName));
            optionalSupplier.ifPresent(setFields());
            return optionalSupplier;
        }
    }

    @Override
    public List<Supplier> retrieveAll() {
        try (SqlSession session = sessionFactory.openSession()) {
            SupplierMapper supplierMapper = session.getMapper(SupplierMapper.class);
            List<Supplier> suppliers = supplierMapper.retrieveAll();
            suppliers.forEach(setFields());
            return suppliers;
        }
    }

    @Override
    public void update(Supplier supplier) {
        try (SqlSession session = sessionFactory.openSession()) {
            SupplierMapper supplierMapper = session.getMapper(SupplierMapper.class);
            supplierMapper.update(supplier);
            session.commit();
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = sessionFactory.openSession()) {
            SupplierMapper supplierMapper = session.getMapper(SupplierMapper.class);
            supplierMapper.delete(id);
            session.commit();
        }
    }

    private Consumer<Supplier> setFields() {
        return supplier -> supplier.setCountry(
                countryService.retrieveById(supplier.getCountryId()).orElse(null)
        );
    }
}
