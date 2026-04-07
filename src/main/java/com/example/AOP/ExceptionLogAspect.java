package com.example.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExceptionLogAspect {

    @AfterThrowing(
            pointcut = "within(com.example.controller..*) || within(com.example.service..*)",
            throwing = "e"
    )
    public void logException(JoinPoint joinPoint, Throwable e) {
        log.error("Exception thrown in {}.{}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e);
    }
}
