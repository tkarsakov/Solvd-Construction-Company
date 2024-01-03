package com.solvd.construction.persistence.mappers;

import com.solvd.construction.model.Project;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProjectMapper {
    @Insert("INSERT INTO projects(finish_date, client_id, start_date, floors, budget, interior_work) " +
            "VALUES (#{finishDate}, #{clientId}, #{startDate}, #{floors}, #{budget}, #{interiorWork})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(Project project);

    @Select("SELECT *, TIMESTAMPDIFF(DAY, start_date, finish_date) AS deadline FROM projects")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "finish_date", property = "finishDate"),
            @Result(column = "client_id", property = "clientId"),
            @Result(column = "start_date", property = "startDate"),
            @Result(column = "floors", property = "floors"),
            @Result(column = "budget", property = "budget"),
            @Result(column = "interior_work", property = "interiorWork"),
            @Result(column = "deadline", property = "deadline")
    })
    List<Project> retrieveAll();

    @Select("SELECT *, TIMESTAMPDIFF(DAY, start_date, finish_date) AS deadline FROM projects WHERE id = #{id}")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "finish_date", property = "finishDate"),
            @Result(column = "client_id", property = "clientId"),
            @Result(column = "start_date", property = "startDate"),
            @Result(column = "floors", property = "floors"),
            @Result(column = "budget", property = "budget"),
            @Result(column = "interior_work", property = "interiorWork"),
            @Result(column = "deadline", property = "deadline")
    })
    Project retrieveById(Long id);

    @Update("UPDATE projects SET finish_date = #{finishDate}, client_id = #{clientId}, start_date = #{startDate}, " +
            "floors = #{floors}, budget = #{budget}, interior_work = #{interiorWork}")
    void update(Project project);

    @Delete("DELETE FROM projects WHERE id = #{id}")
    void delete(Long id);
}
