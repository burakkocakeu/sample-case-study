package com.burakkocak.casestudies.employee.aspect;

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
public class LoggingExceptionHandlerAspect {

    @Pointcut(value = "execution(* com.burakkocak.casestudies.employee.exception.RestExceptionHandler.*(..))")
    private void exceptionsPointcut() {
    }

    @Around("exceptionsPointcut()")
    public Object controllersLogger(ProceedingJoinPoint point) throws Throwable {

        String methodName = point.getSignature().getName();
        String className = point.getTarget().getClass().getSimpleName();

        Object[] args = point.getArgs();
        Exception ex = (Exception) args[0];

        Object o = point.proceed();

        log.info("Exception handled in method {}() of the class {}, Details: {}",
                methodName,
                className,
                ex.getClass().getSimpleName() + ": " + ex);

        ex.printStackTrace();

        return o;

    }

}
