package com.learn.proxy.cglib;

import com.learn.proxy.cglib.classs.*;
import org.apache.ibatis.javassist.*;
import org.apache.ibatis.javassist.bytecode.AnnotationsAttribute;
import org.apache.ibatis.javassist.bytecode.ConstPool;
import org.apache.ibatis.javassist.bytecode.FieldInfo;
import org.apache.ibatis.javassist.bytecode.MethodInfo;
import org.apache.ibatis.javassist.bytecode.annotation.StringMemberValue;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.asm.ClassVisitor;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.core.GeneratorStrategy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.core.type.classreading.AnnotationMetadataReadingVisitor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Map;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-27 11:02
 */
public class Test {

    public static void main(String[] args) throws UnsupportedEncodingException {
        //proxyClass();
       // proxyInstance();

        //updateAnnotationValue();


        try {
           throw new NullPointerException();
        } catch (Exception e) {

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(byteArrayOutputStream);
            e.printStackTrace(printStream);
            String s = byteArrayOutputStream.toString("UTF-8");
            System.out.println(s);
        }
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

    public static void proxyInstance() throws NoSuchFieldException, IllegalAccessException {
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

        Annotation annotation = proxy.getClass().getAnnotation(TestAnnotation.class);
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
        Field values = invocationHandler.getClass().getDeclaredField("memberValues");
        values.setAccessible(true);
        Map<String, Object> memberValues =(Map<String, Object>) values.get(invocationHandler);
        String val = (String) memberValues.get("value");
        System.out.println("改变前:"  + val);
        memberValues.put("value", "来喽来喽1");
        System.out.println("改变后:"  + ((TestAnnotation) annotation).value());

        System.out.println("------------------------------");



        // 创建代理对象
        HelloService proxy2 = (HelloService) enhancer.create();
        System.out.println(proxy == proxy2);
        Annotation annotation2 = proxy2.getClass().getAnnotation(TestAnnotation.class);
        InvocationHandler invocationHandler2 = Proxy.getInvocationHandler(annotation2);
        Field values2 = invocationHandler2.getClass().getDeclaredField("memberValues");
        values2.setAccessible(true);
        Map<String, Object> memberValues2 =(Map<String, Object>) values2.get(invocationHandler2);
        String val2 = (String) memberValues2.get("value");
        System.out.println("改变前:"  + val2);
        memberValues.put("value", "来喽来喽2");
        System.out.println("改变后:"  + ((TestAnnotation) annotation2).value());


        System.out.println("------------");

        Annotation annotation3 = proxy.getClass().getAnnotation(TestAnnotation.class);
        InvocationHandler invocationHandler3 = Proxy.getInvocationHandler(annotation3);
        Field values3 = invocationHandler3.getClass().getDeclaredField("memberValues");
        values3.setAccessible(true);
        Map<String, Object> memberValues3 =(Map<String, Object>) values3.get(invocationHandler3);
        String val3 = (String) memberValues3.get("value");
        System.out.println("改变前3:"  + val3);

        System.out.println("------------------------------");
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


    public static void updateAnnotationValue() throws NoSuchFieldException, IllegalAccessException {
        HelloService hello1 =  new HelloService();

        HelloService hello2 =  new HelloService();


        Annotation annotation = hello1.getClass().getAnnotation(TestAnnotation.class);
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
        Field values = invocationHandler.getClass().getDeclaredField("memberValues");
        values.setAccessible(true);
        Map<String, Object> memberValues =(Map<String, Object>) values.get(invocationHandler);
        String val = (String) memberValues.get("value");
        System.out.println("改变前:"  + val);
        memberValues.put("value", "来喽来喽1");
        System.out.println("改变后:"  + ((TestAnnotation) annotation).value());


        Annotation annotation2 = hello2.getClass().getAnnotation(TestAnnotation.class);
        InvocationHandler invocationHandler2 = Proxy.getInvocationHandler(annotation2);
        Field values2 = invocationHandler2.getClass().getDeclaredField("memberValues");
        values2.setAccessible(true);
        Map<String, Object> memberValues2 =(Map<String, Object>) values2.get(invocationHandler2);
        String val2 = (String) memberValues2.get("value");
        System.out.println("改变前:"  + val2);
        //memberValues.put("value", "来喽来喽1");
        System.out.println("改变后:"  + ((TestAnnotation) annotation2).value());



        HelloService2 hello3 =  new HelloService2();

        Annotation annotation3 = hello3.getClass().getAnnotation(TestAnnotation.class);
        InvocationHandler invocationHandler3 = Proxy.getInvocationHandler(annotation3);
        Field values3 = invocationHandler3.getClass().getDeclaredField("memberValues");
        values3.setAccessible(true);
        Map<String, Object> memberValues3 =(Map<String, Object>) values3.get(invocationHandler3);
        String val3 = (String) memberValues3.get("value");
        System.out.println("改变前:"  + val3);
        //memberValues.put("value", "来喽来喽1");
        System.out.println("改变后:"  + ((TestAnnotation) annotation3).value());

    }





}

