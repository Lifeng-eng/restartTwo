package com.example.handler;

import com.example.common.Result;
import com.example.exception.BusinessException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Result<String> handleBusinessException(BusinessException e) {
        String message = e.getMessage();

        return Result.error(message);
    }


    //字段校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)

    public Result<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        //遍历所有异常并收集拼接成字符串
        String message = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));

        return Result.error(message);

    }
    //兜底异常
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        String message = "系统出错";

        return Result.error(message);
    }

}
