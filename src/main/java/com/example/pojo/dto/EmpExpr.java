package com.example.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

//新增员工
@Data
public class EmpExpr {

    private Integer id;
    /**
     * 头像URL
     */
    private String image;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String name;


    /**
     * 性别 (1: 男, 0: 女)
     */
    private Integer gender;

    /**
     * 职位编码
     */
    private Integer job;

    /**
     * 入职日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate entryDate;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 薪资
     */
    private Integer salary;

    /**
     * 工作经历列表
     */
    List<Expr> exprList;

}
