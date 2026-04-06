package com.example.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 员工实体类
 */
@Data
@JsonPropertyOrder({
        "id", "username", "name", "gender", "image", "job", "salary",
        "entryDate", "deptId", "deptName", "createTime", "updateTime"
})
public class EmployeeVO {

    /**
     * ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别：1 男，2 女
     */
    private Integer gender;

    /**
     * 头像/图像 URL
     */
    private String image;

    /**
     * 职位：
     * 1 - 班主任
     * 2 - 讲师
     * 3 - 学工主管
     * 4 - 教研主管
     * 5 - 咨询师
     */
    private Integer job;

    /**
     * 薪资
     */
    private Integer salary; // 或 BigDecimal，若需高精度

    /**
     * 入职日期（仅日期，如 2020-01-01）
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate entryDate;

    /**
     * 部门 ID
     */
    private Long deptId;

    /**
     * 部门名称（冗余字段，通常用于查询展示）
     */
    private String deptName;

    /**
     * 创建时间（精确到秒）
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间（精确到秒）
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}