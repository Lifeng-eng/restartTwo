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

    public LoginServiceImpl(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    @Override
    public String login(UserLoginDTO userLoginDTO) {
        Emp emp = loginMapper.findByUsername(userLoginDTO);
        if (emp == null) {
            throw new BusinessException("用户名或密码错误");
        }

        if (emp.getPassword().equals(userLoginDTO.getPassword())) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", emp.getId());
            map.put("username", emp.getUsername());

            try {
                return JwtHelper.createToken(map); // 👈 在这里打断点或捕获异常
            } catch (Throwable e) {
                e.printStackTrace(); // 查看具体错误
                throw new RuntimeException("JWT生成失败", e);
            }
        } else {
            throw new BusinessException("用户名或密码错误");
        }
    }
}
