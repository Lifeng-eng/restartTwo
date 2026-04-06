package com.example.pojo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Emp {

    private Integer id;

    private String username;

    private String password;

    private Integer gender;//性别 1男2女

    private String image;

    private Integer job;
    private Integer salary;

    private LocalDateTime entryDate;

    private Integer deptId;

    private String deptName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime  createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
