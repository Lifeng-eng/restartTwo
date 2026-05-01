package com.example.common;

import lombok.Data;

//泛型
@Data
public class Result<T> {

    //响应吗
    private Integer code;
    //响应信息
    private String msg;

    //
    private T data;

    Result() {

    }

    public static <T> Result<T> success() {

       return  success(null);

    }

    public static <T> Result<T> success(T data) {

        return success("操作成功", data);
    }
    public static <P> Result<P> success(String msg, P data) {

        Result<P> result =new Result<>();
        result.setCode(1);
        result.setMsg(msg);
        result.setData(data);

        return result;
    }
    public static <T> Result<T> error(String msg) {

        Result<T> result =new Result<>();

        result.setCode(0);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    //工具方法
    public boolean isSuccess() { return this.code.equals(1);}


}
