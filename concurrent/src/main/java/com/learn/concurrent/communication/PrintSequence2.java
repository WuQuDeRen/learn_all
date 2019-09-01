package com.learn.concurrent.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//
public class PrintSequence2 {

    // 这个共享变量很重要，是控制 先后顺序的关键点之一
    private static int flag = 1;
    private static final Lock lock = new ReentrantLock(false);
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        new Thread(() -> {
            String[] strings = Help.buildCharArr(26);
            for (int i = 0; i < strings.length; i++) {
                lock.lock();
                try {
                    while (flag != 2) {
                        condition.await();
                    }
                    Help.print(strings, i, i+1);
                    flag = 1;
                    condition.signal();
                } catch(Exception e) {

                } finally {
                    lock.unlock();
                }
            }
        }).start();
        new Thread(() -> {
            String[] strings = Help.buildNoArr(52);
            for (int i = 0; i < strings.length; i+=2) {
                lock.lock();
                try {
                    while (flag != 1) {
                        condition.await();
                    }
                    Help.print(strings, i, i+2);
                    flag = 2;
                    condition.signal();
                } catch (Exception e) {} finally {
                    lock.unlock();
                }
            }
        }).start();
    }
}
