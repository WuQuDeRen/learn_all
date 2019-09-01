package com.learn.concurrent.communication;

import java.util.PriorityQueue;
import java.util.Random;

public class Test {
    private static final int queueSize = 10;
    private static volatile PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);

    static class Consumer extends Thread {
        public void run() {
            while (true) {
                synchronized (queue) {
                    System.out.println("消费者似乎");
                    while (queue.size() == 0) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Integer poll = queue.poll();
                    System.out.println(Thread.currentThread().getName() + " 取出值:" + poll + "," + "消费完---当前长度：" + queue.size());
                    queue.notify();
                }
            }
        }
    }
    static class Producer extends Thread {
        public void run() {
            while (true) {
                synchronized (queue) {
                    System.out.println("生产者似乎");
                    while (queue.size() == queueSize) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int off = new Random().nextInt(32768);
                    queue.offer(off);
                    System.out.println("生产值：" + off + "  生产完--当前长度：" + queue.size());
                    queue.notifyAll();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            new Consumer().start();
        }
        new Producer().start();
    }
}
