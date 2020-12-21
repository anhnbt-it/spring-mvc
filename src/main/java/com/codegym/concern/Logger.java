package com.codegym.concern;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;

@Aspect
public class Logger {

    @AfterThrowing(pointcut = "execution(public * com.codegym.service.*.*(..))", throwing = "e")
    public void log(JoinPoint joinPoint, Exception e) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.printf("[CMS] Co loi xay ra: %s.%s%s: %s\n", className, method,args, e.getMessage());
    }

}
