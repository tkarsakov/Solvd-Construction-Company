package com.solvd.construction.service;

import com.solvd.construction.model.Position;

import java.util.List;
import java.util.Optional;

public interface PositionService extends IService<Position> {
    Position create(Position position);

    List<Position> retrieveAll();

    Optional<Position> retrieveByPositionName(String positionName);

    Optional<Position> retrieveById(Long id);
}
