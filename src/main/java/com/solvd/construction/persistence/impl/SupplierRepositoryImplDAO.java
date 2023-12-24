package com.solvd.construction.persistence.impl;

import com.solvd.construction.model.Supplier;
import com.solvd.construction.persistence.SupplierRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SupplierRepositoryImplDAO extends ModelRepositoryImpl<Supplier> implements SupplierRepository {
    private final String TABLE_NAME = "suppliers";
    private final String[] TABLE_COLUMNS = {"supplier_name", "supplier_email", "country_id"};
    private final int[] FIELD_TYPES = {Types.VARCHAR, Types.VARCHAR, Types.BIGINT};

    @Override
    public void create(Supplier supplier) {
        super.create(supplier, TABLE_NAME, TABLE_COLUMNS, FIELD_TYPES);
    }

    @Override
    public Optional<Supplier> findById(Long id) {
        return super.findById(id, TABLE_NAME);
    }

    @Override
    public List<Supplier> findAll() {
        return super.findAll(TABLE_NAME);
    }

    @Override
    public void update(Supplier supplier) {
        super.update(supplier, TABLE_NAME, TABLE_COLUMNS, FIELD_TYPES);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id, TABLE_NAME);
    }

    @Override
    public Object[] getModelParams(Supplier supplier) {
        return new Object[]{supplier.getSupplierName(), supplier.getSupplierEmail(), supplier.getCountryId()};
    }

    @Override
    public Optional<Supplier> getOptionalOfModel(ResultSet resultSet) throws SQLException {
        return Optional.of(
                new Supplier(
                        resultSet.getLong(1),
                        resultSet.getString(TABLE_COLUMNS[0]),
                        resultSet.getString(TABLE_COLUMNS[1]),
                        resultSet.getLong(TABLE_COLUMNS[2])
                )
        );
    }

    @Override
    public List<Supplier> getListOfModel(ResultSet resultSet) throws SQLException {
        List<Supplier> suppliers = new ArrayList<>();
        while (resultSet.next()) {
            suppliers.add(
                    new Supplier(
                            resultSet.getLong(1),
                            resultSet.getString(TABLE_COLUMNS[0]),
                            resultSet.getString(TABLE_COLUMNS[1]),
                            resultSet.getLong(TABLE_COLUMNS[2])
                    )
            );
        }
        return suppliers;
    }
}