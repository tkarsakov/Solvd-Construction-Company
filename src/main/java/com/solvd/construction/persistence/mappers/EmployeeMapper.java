package com.solvd.construction.persistence.mappers;

import com.solvd.construction.model.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface EmployeeMapper {
    @Insert("INSERT INTO employees(first_name, last_name, positionId) VALUES (#{firstName), #{lastName), #{positionId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Employee create(Employee employee);

    @Select("SELECT * FROM employees")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "first_name", property = "firstName"),
            @Result(column = "last_name", property = "lastName"),
            @Result(column = "position_id", property = "positionId")
    })
    List<Employee> retrieveAll();

    @Select("SELECT * FROM employees e WHERE e.id = #{id}")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "first_name", property = "firstName"),
            @Result(column = "last_name", property = "lastName"),
            @Result(column = "position_id", property = "positionId")
    })
    List<Employee> retrieveAllByProjectId(Long id);
}
