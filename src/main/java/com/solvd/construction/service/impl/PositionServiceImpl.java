package com.solvd.construction.service.impl;

import com.solvd.construction.model.Position;
import com.solvd.construction.persistence.PositionRepository;
import com.solvd.construction.persistence.impl.PositionRepositoryImplDAO;
import com.solvd.construction.service.PositionService;

import java.util.List;
import java.util.Optional;

public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;

    public PositionServiceImpl() {
        this.positionRepository = new PositionRepositoryImplDAO();
    }

    @Override
    public Position create(Position position) {
        position.setId(null);
        positionRepository.create(position);
        return position;
    }

    @Override
    public List<Position> retrieveAll() {
        return positionRepository.findAll();
    }

    @Override
    public Optional<Position> retrieveByPositionName(String positionName) {
        return positionRepository.findByPositionName(positionName);
    }

    @Override
    public Optional<Position> retrieveById(Long id) {
        return positionRepository.findById(id);
    }
}
