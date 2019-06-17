package com.learn.design.pattern.behavioral.observer.format;

import com.google.common.collect.Lists;

import java.util.List;

/**
 *  目标又称为主题，它是指被观察的对象。在目标中定义了一个观察者集合，一个观察目标可以接受任意数量的观察者来观察，它提供一下系列方法来增加和删除观察者对象，
 *  同时它定义了通知方法. 目标类可以是接口，也可以是抽象类或具体类
 */

public abstract class Subject {

    /**
     * 定义观察者集合
     */
    protected List<Observer> observerList = Lists.newArrayList();

    /**
     * 注册方法，想观察者集合中添加一个观察者
     * @param observer
     */
    public void attach(Observer observer) {
        observerList.add(observer);
    }

    /**
     * 注销方法，用于在观察者集合中删除一个观察者
     * @param observer
     */
    public void detach(Observer observer) {
         observerList.remove(observer);
    }

    /**
     * 申明抽象通知方法
     */
    public abstract <T> void notifyObservers(T args);

}
