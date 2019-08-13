package com.learn.design.pattern.behavioral.observer.format;

/**
 * 观察者将对观察目标的改变做出反应，观察者一般定义为接口，该接口申明了更新数据的方法,因此又成为抽象观察者
 */
public interface Observer {

    /**
     * 申明响应方法
     * @param observable
     * @param params
     */
    <T> void update(Subject observable, T params);
}
