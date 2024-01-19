package com.solvd.construction.service.impl.jdbc;

import com.solvd.construction.model.SuppliedMaterial;
import com.solvd.construction.persistence.SuppliedMaterialRepository;
import com.solvd.construction.persistence.impl.SuppliedMaterialRepositoryImplDAO;
import com.solvd.construction.service.MaterialNameService;
import com.solvd.construction.service.SuppliedMaterialService;
import com.solvd.construction.service.SupplierService;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class SuppliedMaterialServiceImpl implements SuppliedMaterialService {
    private final SuppliedMaterialRepository suppliedMaterialRepository;
    private final SupplierService supplierService;
    private final MaterialNameService materialNameService;

    public SuppliedMaterialServiceImpl() {
        this.suppliedMaterialRepository = new SuppliedMaterialRepositoryImplDAO();
        this.materialNameService = new MaterialNameServiceImpl();
        this.supplierService = new SupplierServiceImpl();
    }

    @Override
    public SuppliedMaterial create(SuppliedMaterial suppliedMaterial) {
        suppliedMaterial.setId(null);
        suppliedMaterial.setMaterialName(materialNameService.retrieveById(suppliedMaterial.getMaterialNameId()).orElse(null));
        suppliedMaterial.setSupplier(supplierService.retrieveById(suppliedMaterial.getSupplierId()).orElse(null));
        suppliedMaterialRepository.create(suppliedMaterial);
        return suppliedMaterial;
    }

    @Override
    public List<SuppliedMaterial> retrieveAll() {
        return suppliedMaterialRepository.findAll().stream().peek(setFields()).toList();
    }

    @Override
    public Optional<SuppliedMaterial> retrieveById(Long id) {
        Optional<SuppliedMaterial> suppliedMaterialOptional = suppliedMaterialRepository.findById(id);
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
