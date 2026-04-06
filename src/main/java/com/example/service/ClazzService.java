package com.example.service;

import com.example.annoation.AutoInject;
import com.example.common.PageResult;
import com.example.eums.OperationType;
import com.example.pojo.domain.Clazz;
import com.example.pojo.dto.ClazzPageDTO;
import com.example.pojo.dto.ClazzVO;

import java.util.List;

public interface ClazzService {


    PageResult<ClazzVO> getClazzs(ClazzPageDTO clazzPageDTO);

    void deleteClazz(Integer id);


    void addClazz(Clazz clazz);

    ClazzVO findById(Integer id);

    void updateClazz(Clazz clazz);

    List<Clazz> listClazz();
}
