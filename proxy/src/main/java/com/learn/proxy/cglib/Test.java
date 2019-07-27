package com.learn.proxy.cglib;

import com.learn.proxy.cglib.classs.ClassCallbackMethodInterceptor;
import com.learn.proxy.cglib.classs.InstanceCallbackMethodInterceptor;
import com.learn.proxy.cglib.classs.HelloService;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.core.GeneratorStrategy;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-27 11:02
 */
public class Test {

    public static void main(String[] args) {
        //proxyClass();
        proxyInstance();
    }

    public static void proxyClass() {
        // 代理类class文件存入本地磁盘方便我们反编译查看源码
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/tmp");
        // 通过CGLIB动态代理获取代理对象的过程
        Enhancer enhancer = new Enhancer();
        // 设置enhancer对象的父类
        enhancer.setSuperclass(HelloService.class);
        // 设置enhancer的回调对象
        enhancer.setCallback(new ClassCallbackMethodInterceptor());
        // 创建代理对象
        HelloService proxy= (HelloService)enhancer.create();
        // 通过代理对象调用目标方法
        proxy.sayHello();

    }

    public static void proxyInstance() {
        // 代理类class文件存入本地磁盘方便我们反编译查看源码
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/tmp");
        // 通过CGLIB动态代理获取代理对象的过程
        Enhancer enhancer = new Enhancer();

        // 设置enhancer对象的父类
        enhancer.setSuperclass(HelloService.class);
        // 设置enhancer的回调对象
        enhancer.setCallback(new InstanceCallbackMethodInterceptor(new HelloService("我")));
        // 创建代理对象
        HelloService proxy= (HelloService)enhancer.create();
        // 通过代理对象调用目标方法
        proxy.sayHello();
    }


    public static void proxyInstance2() {
        // 代理类class文件存入本地磁盘方便我们反编译查看源码
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/tmp");
        // 通过CGLIB动态代理获取代理对象的过程
        Enhancer enhancer = new Enhancer();

        // 设置enhancer对象的父类
        enhancer.setSuperclass(HelloService.class);
        // 设置enhancer的回调对象
        enhancer.setCallback(new InstanceCallbackMethodInterceptor(new HelloService("我")));
        // 创建代理对象
        HelloService proxy= (HelloService)enhancer.create();



        Enhancer enhancer2 = new Enhancer();

        // 设置enhancer对象的父类
        enhancer2.setSuperclass(HelloService.class);
        // 设置enhancer的回调对象
        enhancer2.setCallback(new InstanceCallbackMethodInterceptor(proxy));
        // 创建代理对象
        HelloService proxy2= (HelloService)enhancer2.create();
        proxy2.sayHello();
    }
}

