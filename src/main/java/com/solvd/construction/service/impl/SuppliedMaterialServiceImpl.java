package com.solvd.construction.service.impl;

import com.solvd.construction.model.SuppliedMaterial;
import com.solvd.construction.persistence.SuppliedMaterialRepository;
import com.solvd.construction.persistence.impl.SuppliedMaterialRepositoryImplDAO;
import com.solvd.construction.service.MaterialNameService;
import com.solvd.construction.service.SuppliedMaterialService;
import com.solvd.construction.service.SupplierService;

import java.util.List;

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
        return null;
    }

    @Override
    public List<SuppliedMaterial> retrieveAll() {
        return null;
    }
}
