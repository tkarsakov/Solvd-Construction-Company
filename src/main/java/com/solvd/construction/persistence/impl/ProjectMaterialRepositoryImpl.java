package com.solvd.construction.persistence.impl;

import com.solvd.construction.model.ProjectMaterial;
import com.solvd.construction.persistence.ProjectMaterialRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectMaterialRepositoryImpl extends ModelRepositoryImpl<ProjectMaterial> implements ProjectMaterialRepository {
    private final String TABLE_NAME = "project_materials";
    private final String[] TABLE_COLUMNS = {"supplied_material_id", "material_amount", "project_id", "measure"};
    private final int[] FIELD_TYPES = {Types.BIGINT, Types.DECIMAL, Types.BIGINT, Types.VARCHAR};

    @Override
    public void create(ProjectMaterial projectMaterial) {
        super.create(projectMaterial, TABLE_NAME, TABLE_COLUMNS, FIELD_TYPES);
    }

    @Override
    public Optional<ProjectMaterial> findById(Long id) {
        return super.findById(id, TABLE_NAME);
    }

    @Override
    public List<ProjectMaterial> findAll() {
        return super.findAll(TABLE_NAME);
    }

    @Override
    public void update(ProjectMaterial projectMaterial) {
        super.update(projectMaterial, TABLE_NAME, TABLE_COLUMNS, FIELD_TYPES);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id, TABLE_NAME);
    }

    @Override
    public Object[] getModelParams(ProjectMaterial projectMaterial) {
        return new Object[]{projectMaterial.getSuppliedMaterialId(), projectMaterial.getMaterialAmount(),
                projectMaterial.getProjectId(), projectMaterial.getMeasure()};
    }

    @Override
    public Optional<ProjectMaterial> getOptionalOfModel(ResultSet resultSet) throws SQLException {
        return Optional.of(
                new ProjectMaterial(
                        resultSet.getLong(TABLE_COLUMNS[0]),
                        resultSet.getBigDecimal(TABLE_COLUMNS[1]),
                        resultSet.getLong(TABLE_COLUMNS[2]),
                        resultSet.getString(TABLE_COLUMNS[3])
                )
        );
    }

    @Override
    public List<ProjectMaterial> getListOfModel(ResultSet resultSet) throws SQLException {
        List<ProjectMaterial> projectMaterials = new ArrayList<>();
        while (resultSet.next()) {
            projectMaterials.add(
                    new ProjectMaterial(
                            resultSet.getLong(TABLE_COLUMNS[0]),
                            resultSet.getBigDecimal(TABLE_COLUMNS[1]),
                            resultSet.getLong(TABLE_COLUMNS[2]),
                            resultSet.getString(TABLE_COLUMNS[3])
                    )
            );
        }
        return projectMaterials;
    }
}