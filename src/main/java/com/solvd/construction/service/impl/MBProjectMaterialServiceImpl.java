package com.solvd.construction.service.impl;

import com.solvd.construction.model.ProjectMaterial;
import com.solvd.construction.persistence.mappers.ProjectMaterialMapper;
import com.solvd.construction.service.ProjectMaterialService;
import com.solvd.construction.service.SuppliedMaterialService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MBProjectMaterialServiceImpl implements ProjectMaterialService {
    private final SqlSessionFactory sessionFactory;
    private final SuppliedMaterialService suppliedMaterialService;

    public MBProjectMaterialServiceImpl(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.suppliedMaterialService = new MBSuppliedMaterialServiceImpl(sessionFactory);
    }

    @Override
    public ProjectMaterial create(ProjectMaterial projectMaterial) {
        try (SqlSession session = sessionFactory.openSession()) {
            ProjectMaterialMapper projectMaterialMapper = session.getMapper(ProjectMaterialMapper.class);
            projectMaterial.setId(null);
            projectMaterial.setSuppliedMaterial(suppliedMaterialService.retrieveById(projectMaterial.getSuppliedMaterialId()).orElse(null));
            projectMaterialMapper.create(projectMaterial);
            session.commit();
            return projectMaterial;
        }
    }

    @Override
    public List<ProjectMaterial> retrieveAllByProjectId(Long projectId) {
        try (SqlSession session = sessionFactory.openSession()) {
            ProjectMaterialMapper projectMaterialMapper = session.getMapper(ProjectMaterialMapper.class);
            List<ProjectMaterial> projectMaterials = projectMaterialMapper.retrieveAllByProjectId(projectId);
            for (var projectMaterial : projectMaterials) {
                projectMaterial.setSuppliedMaterial(
                        suppliedMaterialService
                                .retrieveById(projectMaterial.getSuppliedMaterialId())
                                .orElse(null)
                );
            }
            return projectMaterials;
        }
    }

    @Override
    public List<ProjectMaterial> retrieveAll() {
        try (SqlSession session = sessionFactory.openSession()) {
            ProjectMaterialMapper projectMaterialMapper = session.getMapper(ProjectMaterialMapper.class);
            List<ProjectMaterial> projectMaterials = projectMaterialMapper.retrieveAll();
            for (var projectMaterial : projectMaterials) {
                projectMaterial.setSuppliedMaterial(
                        suppliedMaterialService
                                .retrieveById(projectMaterial.getSuppliedMaterialId())
                                .orElse(null)
                );
            }
        }
        return null;
    }

    @Override
    public void update(ProjectMaterial projectMaterial) {
        try (SqlSession session = sessionFactory.openSession()) {
            ProjectMaterialMapper projectMaterialMapper = session.getMapper(ProjectMaterialMapper.class);
            projectMaterialMapper.update(projectMaterial);
            session.commit();
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = sessionFactory.openSession()) {
            ProjectMaterialMapper projectMaterialMapper = session.getMapper(ProjectMaterialMapper.class);
            projectMaterialMapper.delete(id);
            session.commit();
        }
    }
}
