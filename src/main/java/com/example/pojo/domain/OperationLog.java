package com.example.pojo.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OperationLog {

    private Long id;

    private Long operatorId;

    private String operatorName;

    private String operation;

    private String entity;

    private String description;

    private LocalDateTime createTime;
}
