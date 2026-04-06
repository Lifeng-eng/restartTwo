package com.example.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDate;

@Data
public class EmployeeQueryDTO {

    private String name;

    private Integer gender;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin ;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;

    private Integer page = 1;
    private Integer pageSize = 10;
}