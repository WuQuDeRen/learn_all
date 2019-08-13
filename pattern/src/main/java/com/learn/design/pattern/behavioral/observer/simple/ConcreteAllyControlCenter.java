package com.learn.design.pattern.behavioral.observer.simple;

import org.apache.commons.lang3.StringUtils;

public class ConcreteAllyControlCenter extends  AllyControlCenter {

    @Override
    void notifyObserver(String name) {
        for (Observer observer : players) {
            if (!StringUtils.equals(name, observer.getName())) {
                observer.helpYou();
            }
        }
    }
}
