package com.example.controller;

import com.example.common.Result;
import com.example.pojo.domain.Dept;
import com.example.service.DeptService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/depts")

public class DeptController {


    private final DeptService deptService;

    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }

    @GetMapping()

    //该接口用于部门列表数据查询
    public Result<List<Dept>> getDepts() {

        List<Dept> depts = deptService.getDepts();

        return Result.success(depts);
    }
    //该接口用于根据ID删除部门数据

    @DeleteMapping()
    public Result<String> deleteDept(Integer id) {

        deptService.deleteDept(id);

        return Result.success("success",null);

    }
    //该接口用于添加部门数据

    @PostMapping()
    public Result<String> addDept(@RequestBody Dept dept) {

        deptService.addDept(dept);

        return Result.success("success",null);
    }
    //该接口用于根据ID查询部门数据

    @GetMapping("/{id}")
    public Result<Dept> getById(@PathVariable Integer id) {

        Dept dept = deptService.getById(id);

        return Result.success(dept);


    }
    //该接口用于修改部门数据
    @PutMapping()
    public Result<Void> updateDept(@RequestBody Dept dept) {

        deptService.updateById(dept);
        return Result.success("success",null);
    }





}
