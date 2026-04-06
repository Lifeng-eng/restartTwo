package com.example.service;

import com.example.common.PageResult;
import com.example.pojo.dto.EmpExpr;
import com.example.pojo.dto.EmpSaveDTO;
import com.example.pojo.dto.EmployeeQueryDTO;
import com.example.pojo.vo.EmployeeVO;

import java.util.List;

public interface EmpService {

    PageResult<EmployeeVO> empPageQuery(EmployeeQueryDTO employeeQueryDTO);

    void empDelete(List<Integer> ids);

    void empAdd(EmpExpr empExpr);

    EmployeeVO findById(Integer id);

    void saveEmp(EmpSaveDTO empSaveDTO);

    List<EmployeeVO> getEmpList();
}
