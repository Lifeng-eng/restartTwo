// com.example.aspect.AuditAspect
package com.example.AOP;

import com.example.annoation.AutoInject;
import com.example.common.Audit;
import com.example.eums.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collection;

@Aspect
@Component
@Slf4j
public class AuditAspect {

    @Before("@annotation(autoInject)")
    public void injectAuditFields(JoinPoint joinPoint, AutoInject autoInject) {//方法本身与注解对象
        OperationType operationType = autoInject.value();
        LocalDateTime now = LocalDateTime.now();

        // 遍历所有方法参数
        for (Object arg : joinPoint.getArgs()) {
            if (arg == null) continue;

            // 情况1：参数本身实现了 Audit
            if (arg instanceof Audit) {
                handleAuditObject((Audit) arg, operationType, now);
            }
            // 情况2：参数是 Collection，且泛型元素可能是 Audit
            else if (arg instanceof Collection<?>) {
                handleCollection((Collection<?>) arg, operationType, now);
            }
            // 可扩展：数组、Map 等（按需添加）
        }
    }

    private void handleAuditObject(Audit audit, OperationType operationType, LocalDateTime now) {
        if (operationType == OperationType.INSERT) {
            // INSERT 时才设置 createTime（避免 update 时覆盖）
            if (audit.getCreateTime() == null) {
                audit.setCreateTime(now);
            }
            audit.setUpdateTime(now);
        } else if (operationType == OperationType.UPDATE) {
            audit.setUpdateTime(now);
            // 不修改 createTime
        }
        log.debug("已注入审计字段: operation={}, createTime={}, updateTime={}",
                operationType, audit.getCreateTime(), audit.getUpdateTime());
    }

    private void handleCollection(Collection<?> collection, OperationType operationType, LocalDateTime now) {
        if (CollectionUtils.isEmpty(collection)) return;

        for (Object item : collection) {
            if (item instanceof Audit) {
                handleAuditObject((Audit) item, operationType, now);
            }
        }
    }
}