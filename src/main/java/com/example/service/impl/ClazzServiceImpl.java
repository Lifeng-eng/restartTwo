package com.example.service.impl;

import com.example.annoation.AutoInject;
import com.example.common.PageResult;
import com.example.eums.OperationType;
import com.example.mapper.ClazzMapper;
import com.example.pojo.domain.Clazz;
import com.example.pojo.dto.ClazzPageDTO;
import com.example.pojo.dto.ClazzVO;
import com.example.service.ClazzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    private final ClazzMapper clazzMapper;

    public ClazzServiceImpl(ClazzMapper clazzMapper) {
        this.clazzMapper = clazzMapper;
    }

    @Transactional
    @Override
    public PageResult<ClazzVO> getClazzs(ClazzPageDTO clazzPageDTO) {
        // 1. 计算分页起始位置
        int start = (clazzPageDTO.getPage() - 1) * clazzPageDTO.getPageSize();
        clazzPageDTO.setStart(start); // 需在 ClazzPageDTO 中添加 start 字段

        // 2. 查询当前页数据（已包含 masterName）
        //先查数据
        List<ClazzVO> list = clazzMapper.getClazzs(clazzPageDTO);

        // 3. 查询总记录数
        int total = clazzMapper.countByCondition(clazzPageDTO);

        return new PageResult<>(total, list);
    }

    @Override
    public void deleteClazz(Integer id) {

        clazzMapper.deleteClazz(id);
    }

    @AutoInject(OperationType.INSERT)
    @Override
    public void addClazz(Clazz clazz) {

        clazzMapper.addClazz(clazz);
    }

    @Override
    public ClazzVO findById(Integer id) {
        return clazzMapper.findClazzById(id);
    }

    @AutoInject(OperationType.UPDATE)
    @Override
    public void updateClazz(Clazz clazz) {

        clazzMapper.updateById(clazz);
    }

    @Override
    public List<Clazz> listClazz() {
        return clazzMapper.listClazz();
    }
}
