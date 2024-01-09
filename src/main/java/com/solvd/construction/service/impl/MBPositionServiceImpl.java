package com.solvd.construction.service.impl;

import com.solvd.construction.model.Position;
import com.solvd.construction.persistence.mappers.PositionMapper;
import com.solvd.construction.service.PositionService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Optional;

public class MBPositionServiceImpl implements PositionService {
    private final SqlSessionFactory sessionFactory;

    public MBPositionServiceImpl(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Position create(Position position) {
        try (SqlSession session = sessionFactory.openSession()) {
            PositionMapper positionMapper = session.getMapper(PositionMapper.class);
            position.setId(null);
            positionMapper.create(position);
            session.commit();
            return position;
        }
    }

    @Override
    public List<Position> retrieveAll() {
        try (SqlSession session = sessionFactory.openSession()) {
            PositionMapper positionMapper = session.getMapper(PositionMapper.class);
            return positionMapper.retrieveAll();
        }
    }

    @Override
    public Optional<Position> retrieveByPositionName(String positionName) {
        try (SqlSession session = sessionFactory.openSession()) {
            PositionMapper positionMapper = session.getMapper(PositionMapper.class);
            return Optional.of(positionMapper.retrieveByPositionName(positionName));
        }
    }

    @Override
    public Optional<Position> retrieveById(Long id) {
        try (SqlSession session = sessionFactory.openSession()) {
            PositionMapper positionMapper = session.getMapper(PositionMapper.class);
            return Optional.of(positionMapper.retrieveById(id));
        }
    }
}
