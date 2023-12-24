package com.solvd.construction.persistence.impl;

import com.solvd.construction.model.SuppliedMaterial;
import com.solvd.construction.persistence.SuppliedMaterialRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SuppliedMaterialRepositoryImplDAO extends ModelRepositoryImpl<SuppliedMaterial> implements SuppliedMaterialRepository {
    private final String TABLE_NAME = "supplied_materials";
    private final String[] TABLE_COLUMNS = {"material_name_id", "supplier_id", "price"};
    private final int[] FIELD_TYPES = {Types.BIGINT, Types.BIGINT, Types.DECIMAL};

    @Override
    public void create(SuppliedMaterial suppliedMaterial) {
        super.create(suppliedMaterial, TABLE_NAME, TABLE_COLUMNS, FIELD_TYPES);
    }

    @Override
    public Optional<SuppliedMaterial> findById(Long id) {
        return super.findById(id, TABLE_NAME);
    }

    @Override
    public List<SuppliedMaterial> findAll() {
        return super.findAll(TABLE_NAME);
    }

    @Override
    public void update(SuppliedMaterial suppliedMaterial) {
        super.update(suppliedMaterial, TABLE_NAME, TABLE_COLUMNS, FIELD_TYPES);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id, TABLE_NAME);
    }


    @Override
    public Object[] getModelParams(SuppliedMaterial suppliedMaterial) {
        return new Object[]{suppliedMaterial.getMaterialNameId(), suppliedMaterial.getSupplierId(),
                suppliedMaterial.getPrice()};
    }

    @Override
    public Optional<SuppliedMaterial> getOptionalOfModel(ResultSet resultSet) throws SQLException {
        return Optional.of(
                new SuppliedMaterial(
                        resultSet.getLong(1),
                        resultSet.getLong(TABLE_COLUMNS[0]),
                        resultSet.getLong(TABLE_COLUMNS[1]),
                        resultSet.getBigDecimal(TABLE_COLUMNS[2])
                )
        );
    }

    @Override
    public List<SuppliedMaterial> getListOfModel(ResultSet resultSet) throws SQLException {
        List<SuppliedMaterial> suppliedMaterials = new ArrayList<>();
        while (resultSet.next()) {
            suppliedMaterials.add(
                    new SuppliedMaterial(
                            resultSet.getLong(1),
                            resultSet.getLong(TABLE_COLUMNS[0]),
                            resultSet.getLong(TABLE_COLUMNS[1]),
                            resultSet.getBigDecimal(TABLE_COLUMNS[2])
                    )
            );
        }
        return suppliedMaterials;
    }
}