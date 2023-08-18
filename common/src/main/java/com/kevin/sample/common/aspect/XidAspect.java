package com.kevin.sample.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class XidAspect {
    @Around("@annotation(com.kevin.sample.common.annotation.Xid)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("proceed....");
        return joinPoint.proceed();
    }
}
