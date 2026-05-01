package com.example.common;

import java.util.HashMap;
import java.util.Map;

public enum ResponseCode {

    SUCCESS(200, "操作成功"),
    PARAM_ERROR(400, "请求参数错误"),
    UNAUTHORIZED(401, "未登录"),
    FORBIDDEN(403, "无访问权限"),
    NOT_FOUND(404, "资源不存在"),
    SERVER_ERROR(500, "服务器内部错误");

    private final int code;
    private final String msg;

    private static final Map<Integer, ResponseCode> CODE_CACHE = new HashMap<>();

    static {
        for (ResponseCode code : values()) {
            CODE_CACHE.put(code.code, code);
        }
    }

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static ResponseCode getByCode(int code) {
        return CODE_CACHE.get(code);
    }
//    // 快速构建返回结果
//    public <T> Result<T> build(T data) {
//        return new Result<>(this.code, this.msg, data);
//    }

}
