package com.example.AOP;

import com.example.annoation.OperationLog;
import com.example.context.BaseContext;
import com.example.eums.OperationAction;
import com.example.mapper.OperationLogMapper;
import com.example.pojo.domain.OperationLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class OperationLogAspect {

    private final OperationLogMapper operationLogMapper;

    public OperationLogAspect(OperationLogMapper operationLogMapper) {
        this.operationLogMapper = operationLogMapper;
    }

    @Around("@annotation(operationLog)")
    public Object recordOperation(ProceedingJoinPoint joinPoint, OperationLog operationLog) throws Throwable {
        Object result = joinPoint.proceed();

        try {
            OperationLog log = new OperationLog();
            log.setOperatorId(BaseContext.getCurrentId());
            log.setOperatorName(BaseContext.getCurrentUsername());

            OperationAction action = operationLog.operation();
            log.setOperation(action.name());
            log.setEntity(operationLog.entity());

            String opChinese = action == OperationAction.CREATE ? "新增" : "删除";
            String template = operationLog.description();
            String description = template
                    .replace("{operator}", log.getOperatorName() != null ? log.getOperatorName() : "未知")
                    .replace("{op}", opChinese)
                    .replace("{entity}", log.getEntity())
                    .replace("{targetId}", resolveTargetId(joinPoint, action));
            log.setDescription(description);

            log.setCreateTime(LocalDateTime.now());

            operationLogMapper.insert(log);
        } catch (Exception e) {
            log.error("Failed to record operation log: {}", e.getMessage());
        }

        return result;
    }

    private String resolveTargetId(ProceedingJoinPoint joinPoint, OperationAction action) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg == null) continue;
            if (arg instanceof Number) return arg.toString();
        }
        return "unknown";
    }
}
