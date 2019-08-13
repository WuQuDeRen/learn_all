package com.learn.system.util.excel;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: Excel工具类 导入导出
 * @Author: WindPursuer
 * @Date 2018/7/17 下午2:54
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Excel {

    /**
     * 列名
     */
    String cellName() default "";

    /**
     * 列数（第几列）
     */
    int cellNum() default 0;
}
