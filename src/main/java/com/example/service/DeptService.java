package com.example.service;

import com.example.common.Result;
import com.example.pojo.domain.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> getDepts();

    void deleteDept(Integer id);

    void addDept(Dept dept);

    Dept getById(Integer id);

    void updateById(Dept dept);
}
