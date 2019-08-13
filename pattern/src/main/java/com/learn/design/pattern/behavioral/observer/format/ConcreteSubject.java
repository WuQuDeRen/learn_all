package com.learn.design.pattern.behavioral.observer.format;

/**
 * 具体目标是目标累的子类，通常它包含有经常发生改变的数据，当它的状态发生改变时，向他的各个观察者发出通知；
 * 同时它还实现了在目标类中定义的抽象业务逻辑方法（如有的话）。如果无需扩展目标类，则具体目标类可以省略
 *
 */
public class ConcreteSubject extends Subject {

    @Override
    public <T> void notifyObservers(T args) {
        for (Observer observer : observerList) {
            observer.update(this, args);
        }
    }


}
