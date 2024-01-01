package com.technicaltest.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Aspect
@Component
public class ReportLogger {
    @Around("execution(* com.technicaltest..*.*(..))")
    public Object executionTimeLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodSignature = joinPoint.getSignature().toString();
        log.info(methodSignature + " execution started...");

        Instant begin = Instant.now();
        Object retObj = joinPoint.proceed();
        long duration = Duration.between(begin, Instant.now()).toMillis();
        log.info(methodSignature + " execution end");
        log.info(String.format("Time taken to execute " + methodSignature + " : %dms", duration));
        return retObj;
    }

    @AfterThrowing(value = "execution(* com.technicaltest..*.*(..))", throwing = "ex")
    public void exceptionLogging(JoinPoint joinPoint, Exception ex) {
        log.error("Error while executing " + joinPoint.getSignature().toString() + " : " + ex.getMessage());
    }

}
