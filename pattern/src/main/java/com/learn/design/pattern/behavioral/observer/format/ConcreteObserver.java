package com.learn.design.pattern.behavioral.observer.format;

/**
 * 具体观察者，在具体观察者中维护一个指向具体目标对象的引用，它在存储具体观察者的有关状态，这些状态需要和具体目标的状态保持一致；它实现了在抽象观察者中定义的方法。
 * 通常在在实现时，可地哦啊用具体目标累的方法将自己添加到目标类的集合中或通过删除方法将自己从目标类的集合中删除。
 */
public class ConcreteObserver implements Observer {

    @Override
    public <T> void update(Subject observable, T params) {
        System.out.println("desc => 观察者状态更新 params => observable -> " + observable + " params -> " +params + "");
    }
}
