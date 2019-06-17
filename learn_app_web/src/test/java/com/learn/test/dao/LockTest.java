package com.learn.test.dao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockTest {
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args)  {
        final LockTest test = new LockTest();

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                test.get(Thread.currentThread());
            });
        }

    }

    public synchronized void get(Thread thread) {
        rwl.readLock().lock();
        try {
            long start = System.currentTimeMillis();
            while(System.currentTimeMillis() - start <= 100) {
                System.out.println(thread.getName() + " 正在进行读操作");
            }
            System.out.println(thread.getName() +" ==========》读操作完毕《============");
        } catch (Exception e) {

        } finally {
            rwl.readLock().unlock();
        }

    }
}
