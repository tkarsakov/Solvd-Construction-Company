package com.solvd.construction.persistence.mappers;

import com.solvd.construction.model.ProjectMaterial;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProjectMaterialMapper {
    @Insert("INSERT INTO project_materials(supplied_material_id, material_amount, project_id, measure) "
            + "VALUES (#{suppliedMaterialId}, #{materialAmount}, #{projectId}, #{measure})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    ProjectMaterial create(ProjectMaterial projectMaterial);

    @Select("SELECT * FROM project_materials WHERE project_id = #{projectId}")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "supplied_material_id", property = "suppliedMaterialId"),
            @Result(column = "material_amount", property = "materialAmount"),
            @Result(column = "project_id", property = "projectId"),
            @Result(column = "measure", property = "measure")
    })
    List<ProjectMaterial> retrieveAllByProjectId(Long projectId);
}
