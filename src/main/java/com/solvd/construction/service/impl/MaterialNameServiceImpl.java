package com.solvd.construction.service.impl;

import com.solvd.construction.model.MaterialName;
import com.solvd.construction.persistence.MaterialNameRepository;
import com.solvd.construction.persistence.impl.MaterialNameRepositoryImplDAO;
import com.solvd.construction.service.MaterialNameService;

import java.util.Optional;

public class MaterialNameServiceImpl implements MaterialNameService {
    private final MaterialNameRepository materialNameRepository;

    public MaterialNameServiceImpl() {
        this.materialNameRepository = new MaterialNameRepositoryImplDAO();
    }

    @Override
    public MaterialName create(MaterialName materialName) {
        return null;
    }

    @Override
    public Optional<MaterialName> retrieveById(Long id) {
        return materialNameRepository.findById(id);
    }
}
