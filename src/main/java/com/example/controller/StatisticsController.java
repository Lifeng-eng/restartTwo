package com.example.controller;

import com.example.common.Result;
import com.example.mapper.DeptMapper;
import com.example.pojo.vo.EmpGenderDataVO;
import com.example.pojo.vo.EmpJobDataVO;
import com.example.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/report")
public class StatisticsController {

    private final ReportService reportService;

    public StatisticsController(ReportService reportService) {
        this.reportService = reportService;
    }

    //统计员工性别信息
    @GetMapping("/empGenderData")
    public Result<List<EmpGenderDataVO>> getEmpGenderData() {


        List<EmpGenderDataVO> empGenderDataVOS = reportService.countByGender();


        return Result.success(empGenderDataVOS);


    }

    //统计各个职位的员工人数
    @GetMapping("/empJobData")
    public Result<EmpJobDataVO> getEmpJobData() {

        EmpJobDataVO stats = reportService.getJobStatistics();

        return Result.success(stats);
    }


}
