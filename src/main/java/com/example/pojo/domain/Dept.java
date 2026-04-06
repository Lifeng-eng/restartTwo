package com.example.pojo.domain;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class Dept {

    private Integer id;

    private String name;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
