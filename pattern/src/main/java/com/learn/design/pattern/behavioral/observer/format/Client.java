package com.learn.design.pattern.behavioral.observer.format;

/**
 * 观察者模式
 *
 * 1、
 *  目标又称为主题，它是指被观察的对象。在目标中定义了一个观察者集合，一个观察目标可以接受任意数量的观察者来观察，它提供一下系列方法来增加和删除观察者对象，
 *  同时它定义了通知方法. 目标类可以是接口，也可以是抽象类或具体类
 *
 * 2、
 *  观察者将对观察目标的改变做出反应，观察者一般定义为接口，该接口申明了更新数据的方法,因此又成为抽象观察者
 */
public class Client {

    public static void main(String[] args) {

        Observer observer1 = new ConcreteObserver();
        Observer observer2 = new ConcreteObserver();

        Subject sub = new ConcreteSubject();

        sub.attach(observer1);
        sub.attach(observer2);


        sub.notifyObservers("12");
    }
}
