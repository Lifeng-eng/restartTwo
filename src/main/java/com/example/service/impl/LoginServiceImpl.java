package com.example.service.impl;

import com.example.exception.BusinessException;
import com.example.mapper.LoginMapper;
import com.example.pojo.domain.Emp;
import com.example.pojo.dto.UserLoginDTO;
import com.example.service.LoginService;
import com.example.utils.JwtHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

    private final LoginMapper loginMapper;
    private final JwtHelper jwtHelper;

    public LoginServiceImpl(LoginMapper loginMapper, JwtHelper jwtHelper) {
        this.loginMapper = loginMapper;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public String login(UserLoginDTO userLoginDTO) {
        Emp emp = loginMapper.findByUsername(userLoginDTO);
        if (emp == null) {
            throw new BusinessException("用户名或密码错误");
        }

        if (emp.getPassword().equals(userLoginDTO.getPassword())) {
            Map<String, Object> map = new HashMap<>();
            map.put("userId", emp.getId());
            map.put("username", emp.getUsername());

            try {
                return jwtHelper.createToken(map);
            } catch (Throwable e) {
                log.error("JWT生成失败", e);
                throw new RuntimeException("JWT生成失败", e);
            }
        } else {
            throw new BusinessException("用户名或密码错误");
        }
    }
}
