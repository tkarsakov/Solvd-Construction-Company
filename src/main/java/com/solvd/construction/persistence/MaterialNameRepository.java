package com.solvd.construction.persistence;

import com.solvd.construction.model.MaterialName;

import java.util.List;
import java.util.Optional;

public interface MaterialNameRepository {
    void create(MaterialName materialName);

    Optional<MaterialName> findById(Long id);

    List<MaterialName> findAll();

    void update(MaterialName materialName);

    void deleteById(Long id);
}
