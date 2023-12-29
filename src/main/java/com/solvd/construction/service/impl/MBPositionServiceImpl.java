package com.solvd.construction.service.impl;

import com.solvd.construction.model.Position;
import com.solvd.construction.service.PositionService;
import com.solvd.construction.persistence.mappers.PositionMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class MBPositionServiceImpl implements PositionService {
    private final PositionMapper positionMapper;

    public MBPositionServiceImpl(SqlSession session) {
        this.positionMapper = session.getMapper(PositionMapper.class);
    }

    @Override
    public Position create(Position position) {
        position.setId(null);
        return positionMapper.create(position);
    }

    @Override
    public List<Position> retrieveAll() {
        return positionMapper.retrieveAll();
    }

    @Override
    public Optional<Position> retrieveByPositionName(String positionName) {
        return Optional.of(positionMapper.retrieveByPositionName(positionName));
    }

    @Override
    public Optional<Position> retrieveById(Long id) {
        return Optional.of(positionMapper.retrieveById(id));
    }
}
