package com.example.service.impl;

import com.example.mapper.ReportMapper;
import com.example.pojo.vo.EmpGenderDataVO;
import com.example.pojo.vo.EmpJobDataVO;
import com.example.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {


    private final ReportMapper reportMapper;

    public ReportServiceImpl(ReportMapper reportMapper) {
        this.reportMapper = reportMapper;
    }

    @Override
    public List<EmpGenderDataVO> countByGender() {
        return reportMapper.countByGender();
    }

    @Override
    public EmpJobDataVO getJobStatistics() {

        List<Map<String,Object>> jobStats = reportMapper.countByJob();

        EmpJobDataVO result = new EmpJobDataVO();
        List<String> jobList = new ArrayList<>();
        List<Integer> dataList = new ArrayList<>();

        for (Map<String, Object> stat : jobStats) {
            String jobName =(String)stat.get("jobName");
            Integer count = ((Number) stat.get("employeeCount")).intValue();

            jobList.add(jobName);
            dataList.add(count);
        }

        result.setJobList(jobList);
        result.setDataList(dataList);

        return result;
    }
}
