package com.example.service;

import com.example.pojo.dto.UserLoginDTO;
import jakarta.validation.Valid;

public interface LoginService {
    String login(@Valid UserLoginDTO userLoginDTO);
}
