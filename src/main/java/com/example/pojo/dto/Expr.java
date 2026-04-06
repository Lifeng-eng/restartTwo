package com.example.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Expr {
    private Integer empId;
    private String company;
    private String job;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;
}
