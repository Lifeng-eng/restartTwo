package com.example.service;

import com.example.pojo.vo.EmpGenderDataVO;
import com.example.pojo.vo.EmpJobDataVO;

import java.util.List;

public interface ReportService {
    List<EmpGenderDataVO> countByGender();

    EmpJobDataVO getJobStatistics();
}
