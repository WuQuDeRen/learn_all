package com.learn.design.pattern.structural.bridge.common;

public class ConcreteImplementorB implements Implementor {
    @Override
    public void operationImpl() {
        System.out.println("ConcreteImplementorB");
    }
}
