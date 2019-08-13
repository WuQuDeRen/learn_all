package com.learn.aspect;

import com.learn.system.util.DingdingUtil;
import com.learn.system.util.ExceptionUtil;

import com.learn.system.util.JSONUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @desc 异常切面
 * @author ji_fei
 * @date  2019-08-06 09:19
 */
@Aspect
@Component
public class ExceptionAspect {

    ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private Logger logger = LoggerFactory.getLogger(ExceptionAspect.class);

    @Pointcut("execution(* com.learn..*.*(..))")
    public void catchPoint(){}

    @Pointcut("@annotation(com.learn.system.exception.ExceptionAnnotation)")
    public void catchPoint2(){}


    @AfterThrowing(value = "catchPoint2()", throwing = "exception")
    public void afterThrowing(JoinPoint point, Throwable exception) {
        try {
            DingdingUtil.sendMsg("方法：" + point.toString() + "\n参数：" + JSONUtil.toString(point.getArgs()) + "\n异常信息：" + ExceptionUtil.getMessage(exception));
        } catch(Throwable e) {
            logger.error("desc => 异常通知错误", exception);
            logger.error("desc => 异常通知", e);
        }

    }

}
