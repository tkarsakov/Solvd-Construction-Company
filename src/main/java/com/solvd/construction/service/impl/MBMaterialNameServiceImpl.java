package com.solvd.construction.service.impl;

import com.solvd.construction.model.MaterialName;
import com.solvd.construction.service.MaterialNameService;
import com.solvd.construction.persistence.mappers.MaterialNameMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.Optional;

public class MBMaterialNameServiceImpl implements MaterialNameService {
    private final MaterialNameMapper materialNameMapper;

    public MBMaterialNameServiceImpl(SqlSession session) {
        this.materialNameMapper = session.getMapper(MaterialNameMapper.class);
    }

    @Override
    public MaterialName create(MaterialName materialName) {
        materialName.setId(null);
        return materialNameMapper.create(materialName);
    }

    @Override
    public Optional<MaterialName> retrieveById(Long id) {
        return Optional.of(materialNameMapper.retrieveById(id));
    }

    @Override
    public Optional<MaterialName> retrieveByMaterialName(String materialName) {
        return Optional.of(materialNameMapper.retrieveByMaterialName(materialName));
    }
}
