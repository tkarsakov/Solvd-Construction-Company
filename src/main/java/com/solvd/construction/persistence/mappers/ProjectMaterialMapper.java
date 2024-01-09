package com.solvd.construction.persistence.mappers;

import com.solvd.construction.model.ProjectMaterial;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProjectMaterialMapper {
    @Insert("INSERT INTO project_materials(supplied_material_id, material_amount, project_id, measure) "
            + "VALUES (#{suppliedMaterialId}, #{materialAmount}, #{projectId}, #{measure})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(ProjectMaterial projectMaterial);

    @Select("SELECT * FROM project_materials WHERE project_id = #{projectId}")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "supplied_material_id", property = "suppliedMaterialId"),
            @Result(column = "material_amount", property = "materialAmount"),
            @Result(column = "project_id", property = "projectId"),
            @Result(column = "measure", property = "measure")
    })
    List<ProjectMaterial> retrieveAllByProjectId(Long projectId);

    @Select("SELECT * FROM project_materials")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "supplied_material_id", property = "suppliedMaterialId"),
            @Result(column = "material_amount", property = "materialAmount"),
            @Result(column = "project_id", property = "projectId"),
            @Result(column = "measure", property = "measure")
    })
    List<ProjectMaterial> retrieveAll();

    @Update("UPDATE project_materials SET supplied_material_id = #{suppliedMaterialId}, material_amount = #{materialAmount}, " +
            "project_id = #{projectId}, measure = #{measure} WHERE id = #{id}")
    void update(ProjectMaterial projectMaterial);

    @Delete("DELETE FROM project_materials WHERE id = #{id}")
    void delete(Long id);
}
