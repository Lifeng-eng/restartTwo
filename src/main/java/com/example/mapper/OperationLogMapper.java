package com.example.mapper;

import com.example.pojo.domain.OperationLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperationLogMapper {

    void insert(OperationLog operationLog);
}
