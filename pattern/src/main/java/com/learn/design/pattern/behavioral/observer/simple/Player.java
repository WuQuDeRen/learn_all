package com.learn.design.pattern.behavioral.observer.simple;

public class Player implements Observer {

    private String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void helpYou() {
        System.out.println("坚持住，我 " +this.name + " 来帮助你");
    }

    @Override
    public void beAttacked(AllyControlCenter acc) {
        System.out.println("我 " + this.name + " 被攻击");
        acc.notifyObserver(this.name);
    }
}
