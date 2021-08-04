package com.burakkocak.casestudies.employee.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Burak KOCAK
 * @date 7/30/2021
 */
@Aspect
@Component @Slf4j
public class TrackExecutionTimeAspect {

    @Around("@annotation(com.burakkocak.casestudies.employee.annotation.TrackExecutionTime)")
    public Object trackAndLogExecutionTime(ProceedingJoinPoint point) throws Throwable {

        String methodName = point.getSignature().getName();
        String className = point.getTarget().getClass().getSimpleName();

        Long execTimeBegin = System.currentTimeMillis();

        Object o = point.proceed();

        Long execTimeEnd = System.currentTimeMillis();

        log.debug("{}.{}() executed in {} milliseconds...", className, methodName, (execTimeEnd - execTimeBegin));

        return o;

    }

}
