package com.solvd.construction.service.impl.mybatis;

import com.solvd.construction.model.SuppliedMaterial;
import com.solvd.construction.persistence.mappers.SuppliedMaterialMapper;
import com.solvd.construction.service.MaterialNameService;
import com.solvd.construction.service.SuppliedMaterialService;
import com.solvd.construction.service.SupplierService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class MBSuppliedMaterialServiceImpl implements SuppliedMaterialService {
    private final SqlSessionFactory sessionFactory;
    private final MaterialNameService materialNameService;
    private final SupplierService supplierService;

    public MBSuppliedMaterialServiceImpl(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.materialNameService = new MBMaterialNameServiceImpl(sessionFactory);
        this.supplierService = new MBSupplierServiceImpl(sessionFactory);
    }

    @Override
    public SuppliedMaterial create(SuppliedMaterial suppliedMaterial) {
        try (SqlSession session = sessionFactory.openSession()) {
            SuppliedMaterialMapper suppliedMaterialMapper = session.getMapper(SuppliedMaterialMapper.class);
            suppliedMaterial.setId(null);
            suppliedMaterial.setMaterialName(materialNameService.retrieveById(suppliedMaterial.getMaterialNameId()).orElse(null));
            suppliedMaterial.setSupplier(supplierService.retrieveById(suppliedMaterial.getSupplierId()).orElse(null));
            suppliedMaterialMapper.create(suppliedMaterial);
            session.commit();
            return suppliedMaterial;
        }
    }

    @Override
    public List<SuppliedMaterial> retrieveAll() {
        try (SqlSession session = sessionFactory.openSession()) {
            SuppliedMaterialMapper suppliedMaterialMapper = session.getMapper(SuppliedMaterialMapper.class);
            return suppliedMaterialMapper.retrieveAll().stream().peek(setFields()).toList();
        }
    }

    @Override
    public Optional<SuppliedMaterial> retrieveById(Long id) {
        try (SqlSession session = sessionFactory.openSession()) {
            SuppliedMaterialMapper suppliedMaterialMapper = session.getMapper(SuppliedMaterialMapper.class);
            Optional<SuppliedMaterial> suppliedMaterialOptional = Optional.of(suppliedMaterialMapper.retrieveById(id));
            suppliedMaterialOptional.ifPresent(
                    setFields());
            return suppliedMaterialOptional;
        }
    }

    private Consumer<SuppliedMaterial> setFields() {
        return suppliedMaterial -> {
            suppliedMaterial.setMaterialName(
                    materialNameService.retrieveById(
                                    suppliedMaterial.getMaterialNameId()
                            )
                            .orElse(null)
            );
            suppliedMaterial.setSupplier(
                    supplierService.retrieveById(
                                    suppliedMaterial.getSupplierId()
                            )
                            .orElse(null)
            );
        };
    }
}
