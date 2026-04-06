package com.example.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClazzVO {
    private Long id;

    private String name;

    private String room;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime beginDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime endDate;


    private Integer masterId;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private String masterName;


}
