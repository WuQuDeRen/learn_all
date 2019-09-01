package com.learn.concurrent.other;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    private static CountDownLatch latch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            latch.countDown();
            System.out.println("1----完成");
        }).start();
        new Thread(() -> {
            latch.countDown();
            System.out.println("2----完成");
        }).start();

        latch.await();

        System.out.println("任务完成");
    }


}
