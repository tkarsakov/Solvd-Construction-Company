package com.solvd.construction.persistence;

import com.solvd.construction.model.Position;

import java.util.List;
import java.util.Optional;

public interface PositionRepository {
    void create(Position position);

    Optional<Position> findById(Long id);

    List<Position> findAll();

    void update(Position position);

    void deleteById(Long id);
}
