package com.example.service.impl;

import com.example.common.PageResult;
import com.example.mapper.EmpMapper;
import com.example.pojo.dto.EmpExpr;
import com.example.pojo.dto.EmpSaveDTO;
import com.example.pojo.dto.EmployeeQueryDTO;
import com.example.pojo.dto.Expr;
import com.example.pojo.vo.EmployeeVO;
import com.example.service.EmpService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    private final EmpMapper empMapper;



    public EmpServiceImpl(EmpMapper empMapper) {
        this.empMapper = empMapper;
    }
    @Override
    public PageResult<EmployeeVO> empPageQuery(EmployeeQueryDTO employeeQueryDTO) {

        int offset = employeeQueryDTO.getPage()-1;

        int limit = employeeQueryDTO.getPageSize();

        List<EmployeeVO> Result = empMapper.empPageQuery(employeeQueryDTO,offset,limit);

        long total = empMapper.countEmployees(employeeQueryDTO);



        return new PageResult<>(total, Result);
    }

    @Transactional
    @Override
    public void empDelete(List<Integer> ids) {

        //删除员工信息
        empMapper.empDelete(ids);
        for(Integer id : ids){
            //删除工作经历
            empMapper.empExprDelete(id);
        }
    }

    @Override
    @Transactional
    public void empAdd(EmpExpr empExpr) {

        empMapper.insertEmp(empExpr);

        Integer id = empExpr.getId();


        // 2. 插入关联的工作经历
        if (empExpr.getExprList() != null && !empExpr.getExprList().isEmpty()) {
            for (Expr expr : empExpr.getExprList()) {
                // 将主表 ID 设置到工作经历对象中
                // 假设 expr 对象里有一个 empId 字段用于关联
                expr.setEmpId(id);

                empMapper.insertEmpExpr(expr);
            }
        }
    }

    @Override
    public EmployeeVO findById(Integer id) {
        return empMapper.findById(id);
    }

    @Transactional
    @Override
    public void saveEmp(EmpSaveDTO empSaveDTO) {

        //先查数据库中有该用户信息没有如果有，这删除且同步删除工作经历，再重新插入

        EmployeeVO employeeVO = empMapper.findById(empSaveDTO.getId());

        EmpExpr empExpr = new EmpExpr();




            //删除原来的工作经历和员工信息

            Integer id = empSaveDTO.getId();
            List<Integer> list = new ArrayList<>();
            list.add(id);
            empMapper.empDelete(list);
        BeanUtils.copyProperties(empSaveDTO,empExpr);
        empAdd(empExpr);

    }

    @Override
    public List<EmployeeVO> getEmpList() {

        return empMapper.getEmpList();
    }

}
