package com.solvd.construction.persistence.impl;

import com.solvd.construction.model.MaterialName;
import com.solvd.construction.persistence.MaterialNameRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MaterialNameRepositoryImplDAO extends ModelRepositoryImpl<MaterialName> implements MaterialNameRepository {
    private final String TABLE_NAME = "material_names";
    private final String[] TABLE_COLUMNS = {"material_name"};
    private final int[] FIELD_TYPES = {Types.VARCHAR};

    @Override
    public void create(MaterialName materialName) {
        super.create(materialName, TABLE_NAME, TABLE_COLUMNS, FIELD_TYPES);
    }

    @Override
    public Optional<MaterialName> findById(Long id) {
        return super.findById(id, TABLE_NAME);
    }

    @Override
    public List<MaterialName> findAll() {
        return super.findAll(TABLE_NAME);
    }

    @Override
    public void update(MaterialName materialName) {
        super.update(materialName, TABLE_NAME, TABLE_COLUMNS, FIELD_TYPES);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id, TABLE_NAME);
    }

    @Override
    public Optional<MaterialName> findByMaterialName(String materialName) {
        return super.findByUniqueVarchar(materialName, TABLE_NAME, TABLE_COLUMNS[0]);
    }

    @Override
    public Object[] getModelParams(MaterialName materialName) {
        return new Object[]{materialName.getMaterialName()};
    }

    @Override
    public Optional<MaterialName> getOptionalOfModel(ResultSet resultSet) throws SQLException {
        return Optional.of(
                new MaterialName(
                        resultSet.getLong(1),
                        resultSet.getString(TABLE_COLUMNS[0]))
        );
    }

    @Override
    public List<MaterialName> getListOfModel(ResultSet resultSet) throws SQLException {
        List<MaterialName> materialNames = new ArrayList<>();
        while (resultSet.next()) {
            materialNames.add(
                    new MaterialName(
                            resultSet.getLong(1),
                            resultSet.getString(TABLE_COLUMNS[0]))
            );
        }
        return materialNames;
    }
}