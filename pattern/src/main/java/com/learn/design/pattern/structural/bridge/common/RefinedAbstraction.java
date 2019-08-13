package com.learn.design.pattern.structural.bridge.common;

/**
 * 桥接模式中的抽象部分的扩充抽象类: 扩充由Abstraction定义的接口，通常情况下它不再是抽象类而是具体类，
 * 它实现了在Abstraction中声明的抽象业务方法，
 * 在RefinedAbstraction中可以调用在Implementor中定义的业务方法。
 */
public class RefinedAbstraction extends Abstraction {

    @Override
    public void operation() {
        //业务代码
        impl.operationImpl(); //调用实现类的方法
    }
}
