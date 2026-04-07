package com.example.Interceptor;

import com.example.context.BaseContext;
import com.example.exception.BusinessException;
import com.example.pojo.dto.UserInfo;
import com.example.utils.JwtHelper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenInterceptor implements HandlerInterceptor {


    /**
     * 请求处理前：校验 Token 并存储用户信息
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new BusinessException("未登录");
        }

        String token = authorization.substring(7);

        // 【核心调用】一次性获取用户信息
        // 如果返回 null，说明 Token 无效或过期
        UserInfo userInfo = JwtHelper.parseToken(token);

        if (userInfo == null) {
            throw new BusinessException("Token无效或已过期");
        }

        // 将解析出的信息存入 ThreadLocal
        BaseContext.setCurrentId(userInfo.getId());
        BaseContext.setCurrentUsername(userInfo.getUsername());

        return true;
    }
    /**
     * 请求完成后：清理 ThreadLocal
     * 这一步至关重要！Tomcat 等容器使用线程池，线程会被复用。
     * 如果不清理，下一个请求可能会用到这个线程，从而获取到错误的用户信息（串号）。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContext.removeCurrentId();
    }


}
