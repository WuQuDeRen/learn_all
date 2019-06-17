package com.learn.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAop2 {

    @Pointcut("execution(* com.learn.aspect.PersonService.getAb(..))")
    @Order(3)
    public void point(){}


    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println("LogAop2 around 增强处理：" + signature.getName());
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
}
