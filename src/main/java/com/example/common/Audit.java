package com.example.common;

import java.time.LocalDateTime;

public interface Audit {

    LocalDateTime getCreateTime();

    LocalDateTime getUpdateTime();

    void setCreateTime(LocalDateTime createTime);
    void setUpdateTime(LocalDateTime updateTime);


}
