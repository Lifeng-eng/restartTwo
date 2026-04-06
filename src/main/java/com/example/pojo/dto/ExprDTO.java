package com.example.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 员工工作经历 DTO
 */
@Data
public class ExprDTO {

    // 如果是更新操作需要ID，新增时通常不需要
    private Integer id;

    private String company;

    private String job;

    /**
     * 开始时间
     * JSON格式: "2012-07-01"
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;

    /**
     * 结束时间
     * JSON格式: "2015-06-20"
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;

    /**
     * 员工ID
     * 用于关联员工表 (emp.id)
     * 注意：JSON 中为 empId，数据库字段通常为 emp_id
     */
    // @TableField("emp_id") // 如果使用 MyBatis-Plus 且数据库字段是 emp_id，请取消注释此行
    private Integer empId;
}