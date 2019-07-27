package com.learn.proxy.cglib.classs;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-27 11:31
 */
public class InstanceCallbackMethodInterceptor implements MethodInterceptor {

    private HelloService target;

    public InstanceCallbackMethodInterceptor(HelloService target) {
        this.target = target;
    }

    /**
     * MethodProxy类的 invoke 方法是调用  target的实例方法，而 invokeSuper方法 则是直接调用proxy 实例自己的父类方法
     */
    @Override
    public Object intercept(Object proxy, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("-------------");
        // 若果不传入 cglib 动态生成的类的类实例(proxy)，则会报错（可以看cglib 动态生成的字节码，里面有强制类型转换(转换成实际类型)）
        // Object object2 = methodProxy.invokeSuper(proxy, objects);

        // 如果这里的参数传入的是 proxy，则会进行死递归，直至栈溢出
        Object object2 = methodProxy.invoke(target, objects);
        return object2;
    }
}
