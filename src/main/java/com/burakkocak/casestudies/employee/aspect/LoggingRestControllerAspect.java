package com.burakkocak.casestudies.employee.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Burak KOCAK
 * @date 7/30/2021
 */
@Aspect
@Component @Slf4j
public class LoggingRestControllerAspect {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Pointcut(value = "execution(* com.burakkocak.casestudies.employee.controller.*.*(..))")
    private void controllersPointcut() {
    }

    @Around("controllersPointcut()")
    public Object controllersLogger(ProceedingJoinPoint point) throws Throwable {

        String methodName = point.getSignature().getName();
        String className = point.getTarget().getClass().getSimpleName();

        Object[] args = point.getArgs();

        log.info("Incoming message at {}.{}() : {}", className, methodName, MAPPER.writeValueAsString(args));

        Object o = point.proceed();

        log.info("Outgoing message from {}.{}() : {}", className, methodName, MAPPER.writeValueAsString(o));

        return o;

    }

}
