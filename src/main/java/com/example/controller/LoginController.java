package com.example.controller;

import com.example.common.Result;
import com.example.pojo.dto.UserLoginDTO;
import com.example.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @PostMapping
    public Result<String> login(@Valid @RequestBody UserLoginDTO userLoginDTO){


        String token = loginService.login(userLoginDTO);

        return Result.success(token);

    }
}
