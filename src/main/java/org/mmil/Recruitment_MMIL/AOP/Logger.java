package org.mmil.Recruitment_MMIL.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.*;
import java.io.*;

@Slf4j
@Aspect
@Component
public class Logger {

    @Around("execution(* org.mmil.Recruitment_MMIL..*.*(..))")
    public Object logger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Execution of "+proceedingJoinPoint.getSignature().getName() +" started");
        Object j = proceedingJoinPoint.proceed();
        log.info("Execution of "+proceedingJoinPoint.getSignature().getName() +" completed");
        return j;
    }
}
