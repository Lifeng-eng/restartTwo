package com.example.service.impl;

import com.example.mapper.DeptMapper;
import com.example.pojo.domain.Dept;
import com.example.service.DeptService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class DeptServiceImpl implements DeptService {

    private final DeptMapper deptMapper;

    public DeptServiceImpl(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

    @Override
    public List<Dept> getDepts() {

        return deptMapper.getDepts();

    }

    @Override
    public void deleteDept(Integer id) {

        deptMapper.deleteDept(id);
    }

    @Override
    public void addDept(Dept dept) {

        dept.setCreateTime(LocalDateTime.now())
            .setUpdateTime(LocalDateTime.now());
        deptMapper.addDept(dept);
    }

    @Override
    public Dept getById(Integer id) {

        return deptMapper.getById(id);
    }

    @Override
    public void updateById(Dept dept) {

        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.updateById(dept);
    }
}
