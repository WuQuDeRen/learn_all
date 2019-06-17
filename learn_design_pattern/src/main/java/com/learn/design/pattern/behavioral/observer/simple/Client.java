package com.learn.design.pattern.behavioral.observer.simple;

public class Client {

    public static void main(String[] args) {

        AllyControlCenter center = new ConcreteAllyControlCenter();

        Observer obs1 = new Player("队友1");
        Observer obs2 = new Player("队友2");
        Observer obs3 = new Player("队友3");

        center.join(obs1);
        center.join(obs2);
        center.join(obs3);

        obs1.beAttacked(center);
    }
}
