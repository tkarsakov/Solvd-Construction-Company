package com.solvd.construction.persistence.mappers;

import com.solvd.construction.model.Position;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PositionMapper {
    @Insert("INSERT INTO positions(position_name, months_salary) VALUES (#{positionName}, #{monthsSalary})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(Position position);

    @Select("SELECT * FROM positions")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "position_name", property = "positionName"),
            @Result(column = "months_salary", property = "monthsSalary")
    })
    List<Position> retrieveAll();

    @Select("SELECT * FROM positions WHERE position_name = #{positionName}")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "position_name", property = "positionName"),
            @Result(column = "months_salary", property = "monthsSalary")
    })
    Position retrieveByPositionName(String positionName);

    @Select("SELECT * FROM positions WHERE id = #{id}")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "position_name", property = "positionName"),
            @Result(column = "months_salary", property = "monthsSalary")
    })
    Position retrieveById(Long id);
}
