package com.solvd.construction.service.impl;

import com.solvd.construction.model.SuppliedMaterial;
import com.solvd.construction.service.SuppliedMaterialService;
import com.solvd.construction.persistence.mappers.SuppliedMaterialMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class MBSuppliedMaterialServiceImpl implements SuppliedMaterialService {
    private final SuppliedMaterialMapper suppliedMaterialMapper;
    private final MBMaterialNameServiceImpl materialNameService;
    private final MBSupplierServiceImpl supplierService;

    public MBSuppliedMaterialServiceImpl(SqlSession session) {
        this.suppliedMaterialMapper = session.getMapper(SuppliedMaterialMapper.class);
        this.materialNameService = new MBMaterialNameServiceImpl(session);
        this.supplierService = new MBSupplierServiceImpl(session);
    }

    @Override
    public SuppliedMaterial create(SuppliedMaterial suppliedMaterial) {
        suppliedMaterial.setId(null);
        if (supplierService.retrieveBySupplierName(suppliedMaterial.getSupplier().getSupplierName()).isEmpty()) {
            suppliedMaterial.setSupplierId(supplierService.create(suppliedMaterial.getSupplier()).getId());
        }
        if (materialNameService.retrieveByMaterialName(suppliedMaterial.getMaterialName().getMaterialName()).isEmpty()) {
            suppliedMaterial.setMaterialNameId(materialNameService.create(suppliedMaterial.getMaterialName()).getId());
        }
        suppliedMaterialMapper.create(suppliedMaterial);
        return suppliedMaterial;
    }

    @Override
    public List<SuppliedMaterial> retrieveAll() {
        return suppliedMaterialMapper.retrieveAll().stream().peek(setFields()).toList();
    }

    @Override
    public Optional<SuppliedMaterial> retrieveById(Long id) {
        Optional<SuppliedMaterial> suppliedMaterialOptional = Optional.of(suppliedMaterialMapper.retrieveById(id));
        suppliedMaterialOptional.ifPresent(
                setFields());
        return suppliedMaterialOptional;
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
