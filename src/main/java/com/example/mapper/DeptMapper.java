package com.example.mapper;


import com.example.pojo.domain.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select * from dept")
    List<Dept> getDepts();


    @Delete("delete from dept where id=#{id}")
    void deleteDept(Integer id);


    @Insert("insert into dept(name, create_time, update_time) VALUES (#{name},#{createTime},#{updateTime})")
    void addDept(Dept dept);

    @Select("select * from dept where id =#{id}")
    Dept getById(Integer id);

    @Update("update dept set name = #{name},update_time=#{updateTime} where id=#{id}")
    void updateById(Dept dept);
}
