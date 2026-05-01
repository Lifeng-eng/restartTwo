package com.example.controller;

import com.example.annoation.OperationLog;
import com.example.common.PageResult;
import com.example.common.Result;
import com.example.eums.OperationAction;
import com.example.pojo.dto.EmpExpr;
import com.example.pojo.dto.EmpSaveDTO;
import com.example.pojo.dto.EmployeeQueryDTO;
import com.example.pojo.vo.EmployeeVO;
import com.example.service.EmpService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emps")
public class EmpController {

    private final EmpService empService;


    public EmpController(EmpService empService) {
        this.empService = empService;
    }

    //员工分页查询
    @GetMapping
    public Result<PageResult<EmployeeVO>> empPageQuery(EmployeeQueryDTO employeeQueryDTO) {


     PageResult<EmployeeVO> pageResult = empService.empPageQuery(employeeQueryDTO);


     return Result.success(pageResult);
    }
    //该接口用于批量删除员工的数据信息
    @OperationLog(operation = OperationAction.DELETE, entity = "emp", description = "{operator} 删除了员工，ID: {targetId}")
    @DeleteMapping()
    public Result<String> empDelete(@RequestParam("ids") List<Integer> ids) {

        empService.empDelete(ids);

        return Result.success("success",null);
    }
    //该接口用于添加员工的信息
    @OperationLog(operation = OperationAction.CREATE, entity = "emp", description = "{operator} 新增了员工，ID: {targetId}")
    @PostMapping
    public Result<String> empAdd(@RequestBody EmpExpr empExpr) {

        empService.empAdd(empExpr);
        return Result.success("success",null);
    }
    //该接口用于根据主键ID查询员工的信息
    @GetMapping("/{id}")
    public Result<EmployeeVO> empDetail(@PathVariable Integer id) {

        EmployeeVO employeeVO = empService.findById(id);
        return Result.success(employeeVO);
    }

    //该接口用于修改员工的数据信息
    @PutMapping()
    public Result<Void> saveEmp(@RequestBody EmpSaveDTO empSaveDTO) {

        empService.saveEmp(empSaveDTO);
        return Result.success("success",null);

    }
    //该接口用于查询全部员工信息
    @GetMapping("/list")
    public Result<List<EmployeeVO>> empList() {

     List<EmployeeVO> employeeVOS = empService.getEmpList();
        return Result.success(employeeVOS);
    }

}
