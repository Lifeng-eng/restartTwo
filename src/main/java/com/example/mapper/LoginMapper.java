package com.example.mapper;

import com.example.pojo.domain.Emp;
import com.example.pojo.dto.UserLoginDTO;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("select * from emp where username = #{username}")
    Emp findByUsername(UserLoginDTO userLoginDTO);
}
