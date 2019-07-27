package com.learn.proxy.cglib.classs;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-27 11:31
 */
public class ClassCallbackMethodInterceptor implements MethodInterceptor {



    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        Object object1 = methodProxy.invokeSuper(o, objects);

        return object1;
    }
}
