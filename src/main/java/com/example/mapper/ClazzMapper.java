package com.example.mapper;

import com.example.pojo.domain.Clazz;
import com.example.pojo.dto.ClazzPageDTO;
import com.example.pojo.dto.ClazzVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {

    List<ClazzVO> getClazzs(@Param("dto")ClazzPageDTO clazzPageDTO);

    int countByCondition(@Param("dto") ClazzPageDTO clazzPageDTO);


    @Delete("delete from clazz where id = #{id}")
    void deleteClazz(Integer id);

    void addClazz(Clazz clazz);

    @Select("select * from clazz where id = #{id}")
    ClazzVO findClazzById(Integer id);

    void updateById(Clazz clazz);

    @Select("select * from clazz")
    List<Clazz> listClazz();
}
