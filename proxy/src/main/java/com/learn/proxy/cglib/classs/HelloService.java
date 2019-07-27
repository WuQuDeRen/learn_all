package com.learn.proxy.cglib.classs;

import lombok.Data;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-27 11:32
 */
@Data
@TestAnnotation
public class HelloService {

    private String name = "kkkkkkk";



    public HelloService() {
    }

    public HelloService(String name) {
        this.name = name;
    }

    /**
     * 该方法不能被子类覆盖,Cglib是无法代理final修饰的方法的
     */
    final public String sayOthers(String name) {
        System.out.println("HelloService:sayOthers>>"+name);
        return null;
    }

    public void sayHello() {
        System.out.println("HelloService:sayHello______" + name);
    }
}
