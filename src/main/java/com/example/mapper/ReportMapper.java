package com.example.mapper;

import com.example.pojo.vo.EmpGenderDataVO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReportMapper {

    @MapKey("JobName")
    List<Map<String, Object>> countByJob();
    List<EmpGenderDataVO> countByGender();
}
