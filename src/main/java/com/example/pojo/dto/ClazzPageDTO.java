package com.example.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ClazzPageDTO {


    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;

    private Integer page = 1;
    private Integer pageSize = 10;

    // 用于 MyBatis LIMIT
    private Integer start; // 不需要前端传，Service 计算后设置
}
