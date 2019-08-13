package com.learn.design.pattern.behavioral.observer.simple;

import com.google.common.collect.Lists;

import java.util.List;

public abstract class AllyControlCenter {
    protected String allyName;

    protected List<Observer> players = Lists.newArrayList();

    public String getAllyName() {
        return allyName;
    }

    public void setAllyName(String allyName) {
        this.allyName = allyName;
    }

    /**
     * 添加观察者
     * @param obs
     */
    public void join(Observer obs) {
        players.add(obs);
    }

    /**
     * 删除观察者
     * @param obs
     */
    public void quit(Observer obs) {
        players.remove(obs);
    }

    /**
     * 通知观察者，更新状态
     * @param name
     */
    abstract void notifyObserver(String name);

}
