package com.learn.design.pattern.structural.bridge.common;

/**
 *
 * 桥接模式：将 抽象部分 与它的 实现部分 分离，使它们都可以独立地变化。它是一种对象结构型模式，又称为柄体(Handle and Body)模式或接口(Interface)模式。
 *
 * 注释：在使用桥接模式时，我们首先应该识别出一个类所具有的两个独立变化的维度，
 * 将它们设计为两个独立的继承等级结构，为两个维度都提供抽象层，并建立抽象耦合。
 * 通常情况下，我们将具有两个独立变化维度的类的一些普通业务方法和与之关系最密切的维度设计为“抽象类”层次结构(抽象部分)，
 * 而将另一个维度设计为“实现类”层次结构(实现部分)。
 *
 */
public class Client {

    public static void main(String[] args) {

        // 1、实现部分
        Implementor implementor = new ConcreteImplementorA();

        // 2、抽象部分
        Abstraction abstraction =  new RefinedAbstraction();

        abstraction.setImpl(implementor);

        // 3、设置业务方法
        abstraction.operation();
    }
}
