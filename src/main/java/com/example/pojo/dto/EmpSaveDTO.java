package com.example.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

/**
 * 新增员工 DTO (包含工作经历列表)
 */
@Data
public class EmpSaveDTO {

    // ID 通常由数据库自动生成，前端新增时不需要传
    private Integer id;

    /**
     * 用户名 (必须)
     */
    private String username;

    /**
     * 真实姓名 (必须)
     */
    private String name;

    /**
     * 性别 (必须)
     * 1: 男, 2: 女
     */
    private Integer gender;

    /**
     * 密码 (必须)
     * 新增员工时通常需要设置默认密码
     */
    private String password;

    /**
     * 头像地址
     */
    private String image;

    /**
     * 职位 (非必须)
     * 1 班主任, 2 讲师, 3 学工主管, 4 教研主管, 5 咨询师
     */
    private Integer job;

    private String phone;

    /**
     * 薪资 (非必须)
     */
    private Integer salary;

    /**
     * 入职日期 (非必须)
     * JSON格式: "2015-01-01"
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate entryDate;

    /**
     * 部门ID (非必须)
     */
    // @TableField("dept_id") // 如果使用 MyBatis-Plus 且数据库字段是 dept_id，请取消注释此行
    private Integer deptId;

    /**
     * 工作经历列表 (非必须)
     * 对应 JSON 中的 exprList
     */
    private List<Expr> exprList;
}