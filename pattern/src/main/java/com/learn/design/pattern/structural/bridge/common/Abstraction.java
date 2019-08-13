package com.learn.design.pattern.structural.bridge.common;

/**
 * 桥接模式 化继承为聚合
 * 1、
 * 桥接模式中的抽象部分:用于定义抽象类的接口，它一般是抽象类而不是接口，
 * 其中定义了 一个Implementor(实现类接口)类型的对象并可以维护该对象，
 * 它与Implementor之间具有关联关系（聚合），它既可以包含抽象业务方法，
 * 也可以包含具体业务方法。
 *
 * 2、
 * 桥接模式用一种巧妙的方式处理多层继承存在的问题，用抽象关联取代了传统的多层继承，
 * 将类之间的静态继承关系转换为动态的对象组合关系，使得系统更加灵活，并易于扩展，同
 * 时有效控制了系统中类的个数。
 *
 * 3、
 * 在桥接模式结构图中包含如下几个角色:
 * ● Abstraction(抽象类):用于定义抽象类的接口，它一般是抽象类而不是接口，
 *                      其中定义了 一个Implementor(实现类接口)类型的对象并可以维护该对象，
 *                      它与Implementor之间具有关 联关系，它既可以包含抽象业务方法，也可以包含具体业务方法。
 * ● RefinedAbstraction(扩充抽象类):扩充由Abstraction定义的接口，通常情况下它不再是抽象类而是具体类，
 *                      它实现了在Abstraction中声明的抽象业务方法，在RefinedAbstraction中可以 调用在Implementor中定义的业务方法。
 *
 * ● Implementor(实现类接口):定义实现类的接口，这个接口不一定要与Abstraction的接口完 全一致，事实上这两个接口可以完全不同，
 *                          一般而言，Implementor接口仅提供基本操作，而 Abstraction定义的接口可能会做更多更复杂的操作。
 *                          Implementor接口对这些基本操作进行了声 明，而具体实现交给其子类。通过关联关系，在Abstraction中不仅拥有自己的方法，
 *                          还可以调 用到Implementor中定义的方法，使用关联关系来替代继承关系。
 * ● ConcreteImplementor(具体实现类):具体实现Implementor接口，在不同的 ConcreteImplementor中提供基本操作的不同实现，
 *                          在程序运行时，ConcreteImplementor对象将 替换其父类对象，提供给抽象类具体的业务操作方法。
 *
 * 4、
 *  桥接模式是一个非常有用的模式，在桥接模式中体现了很多面向对象设计原则的思想，包括“单一职责原则”、“开闭原则”、“合成复用原则”、“里氏代换原则”、“依赖倒转原则”等。
 *  熟悉桥接模式有助于我们深入理解这些设计原则，也有助于我们形成正确的设计思想和培养良 好的设计风格。
 *
 * 5、
 *  在使用桥接模式时，我们首先应该识别出一个类所具有的两个独立变化的维度，将它们设计为两个独立的继承等级结构，
 *  为两个维度都提供抽象层，并建立抽象耦合。通常情况下，我们将具有两个独立变化维度的类的一些普通业务方法和与之关系最密切的维度设计为“抽象类”层次结构(抽象部分)，
 *  而将另一个维度设计为“实现类”层次结构(实现部分)。
 */
public abstract class Abstraction {

    //定义实现类接口对象
    protected Implementor impl;

    public void setImpl(Implementor impl) {
        this.impl=impl;
    }

    //声明抽象业务方法
    public abstract void operation();
}
