package com.learn.concurrent.communication;

public class NotifyWaitMain {

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        new Thread(() -> {
            int i = 0;
            while (i++ < 1) {
                synchronized (lock) {
                    try {
                        System.out.println("a111111");
                        lock.wait();
                        System.out.println("a222222");
                    } catch (InterruptedException e) {
                        System.out.println("a33333");
                        e.printStackTrace();
                    }
                    System.out.println("a44444");
                }
            }
        }).start();
        Thread.sleep(1000);
        new Thread(() -> {
            int i = 0;
            while (i++ < 1) {
                synchronized (lock) {
                    try {
                        System.out.println("b111111");
                        lock.notify();
                        System.out.println("b222222");
                    } catch (Exception e) {
                        System.out.println("b33333");
                        e.printStackTrace();
                    }
                    System.out.println("b4444444");
                }

            }
        }).start();
    }

}
