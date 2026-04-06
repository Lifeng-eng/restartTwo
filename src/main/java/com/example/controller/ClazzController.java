package com.example.controller;

import com.example.common.PageResult;
import com.example.common.Result;
import com.example.pojo.domain.Clazz;
import com.example.pojo.dto.ClazzPageDTO;
import com.example.pojo.dto.ClazzVO;
import com.example.service.ClazzService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clazzs")
public class ClazzController {


    private final ClazzService clazzService;

    public ClazzController(ClazzService clazzService) {
        this.clazzService = clazzService;
    }
    //该接口用于班级列表数据的条件分页查询

    @GetMapping
    public Result<PageResult<ClazzVO>> getClazzs(ClazzPageDTO clazzPageDTO) {

        PageResult<ClazzVO> pageResult = clazzService.getClazzs(clazzPageDTO);

        return Result.success(pageResult);


    }
    //该接口用于删除班级信息
    @DeleteMapping("/{id}")
    public Result<String> deleteClazz(@PathVariable Integer id) {

        clazzService.deleteClazz(id);
        return Result.success();
    }
//   该接口用于添加班级信息

    @PostMapping()
    public Result<String> addClazz(@RequestBody Clazz clazz) {

        clazzService.addClazz(clazz);
        return Result.success();

    }
    //该接口用于根据主键ID查询班级的信息
    @GetMapping("/{id}")
    public Result<ClazzVO> getClazz(@PathVariable Integer id) {

       ClazzVO clazzVO = clazzService.findById(id);
        return Result.success(clazzVO);
    }
    //该接口用于修改班级的数据信息
    @PutMapping
    public Result<String> updateClazz(@RequestBody Clazz clazz) {

        clazzService.updateClazz(clazz);
        return Result.success();
    }
    //该接口用于查询所有班级信息
    @GetMapping("/list")
    public Result<List<Clazz>> listClazz() {

        List<Clazz> clazzes = clazzService.listClazz();
        return Result.success(clazzes);
    }
}
