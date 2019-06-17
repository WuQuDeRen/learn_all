package com.learn.concurrent;

import org.apache.commons.lang3.RandomUtils;

import java.util.Random;

public class MyThread {

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            while (true) {
                System.out.println(new Random().nextBoolean());
            }
        });

        thread1.start();

    }
}
