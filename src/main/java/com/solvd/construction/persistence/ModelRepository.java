package com.solvd.construction.persistence;

import java.util.List;
import java.util.Optional;

public interface ModelRepository<T> {
    void create(T modelRepr);

    Optional<T> findById(Long id);

    List<T> findAll();

    void update(T modelRepr);

    void deleteById(Long id);
}
