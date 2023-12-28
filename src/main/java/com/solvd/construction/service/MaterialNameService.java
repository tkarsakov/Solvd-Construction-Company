package com.solvd.construction.service;

import com.solvd.construction.model.MaterialName;

import java.util.Optional;

public interface MaterialNameService {
    MaterialName create(MaterialName materialName);

    Optional<MaterialName> retrieveById(Long id);

    Optional<MaterialName> retrieveByMaterialName(String materialName);
}
