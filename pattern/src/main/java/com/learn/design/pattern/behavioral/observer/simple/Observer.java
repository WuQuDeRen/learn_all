package com.learn.design.pattern.behavioral.observer.simple;

public interface Observer {

    String getName();

    void setName(String name);

    void helpYou();

    void beAttacked(AllyControlCenter acc);

}
