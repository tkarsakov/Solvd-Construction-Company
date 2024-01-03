package com.solvd.construction.service.impl;

import com.solvd.construction.model.MaterialName;
import com.solvd.construction.persistence.mappers.MaterialNameMapper;
import com.solvd.construction.service.MaterialNameService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Optional;

public class MBMaterialNameServiceImpl implements MaterialNameService {
    private final SqlSessionFactory sessionFactory;

    public MBMaterialNameServiceImpl(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public MaterialName create(MaterialName materialName) {
        try (SqlSession session = sessionFactory.openSession()) {
            MaterialNameMapper materialNameMapper = session.getMapper(MaterialNameMapper.class);
            materialName.setId(null);
            return materialNameMapper.create(materialName);
        }
    }

    @Override
    public Optional<MaterialName> retrieveById(Long id) {
        try (SqlSession session = sessionFactory.openSession()) {
            MaterialNameMapper materialNameMapper = session.getMapper(MaterialNameMapper.class);
            return Optional.of(materialNameMapper.retrieveById(id));
        }
    }

    @Override
    public Optional<MaterialName> retrieveByMaterialName(String materialName) {
        try (SqlSession session = sessionFactory.openSession()) {
            MaterialNameMapper materialNameMapper = session.getMapper(MaterialNameMapper.class);
            return Optional.of(materialNameMapper.retrieveByMaterialName(materialName));
        }
    }
}
