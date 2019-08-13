package com.learn.concurrent;
/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-17 11:09
 */
public class TestThread {

    public static void main(String[] args) {
        Object aLock = new Object();
        Object bLock = new Object();


        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (aLock) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (bLock) {
                        System.out.print(2);
                        bLock.notify();
                    }
                    try {
                        aLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (bLock) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (aLock) {
                        System.out.print(1);
                        aLock.notify();
                    }
                    try {
                        bLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
