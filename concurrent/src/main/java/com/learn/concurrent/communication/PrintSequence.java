package com.learn.concurrent.communication;

/**
 *  线程协作打印：12A34B56C78D910E1112F1314G1516H1718I1920J2122K2324L2526M2728N2930O3132P3334Q3536R3738S3940T4142U4344V4546W4748X4950Y5152Z
 *  A线程打印：数字
 *  B线程打印：字母
 *  关键点：要有标志位 flag来标志需要哪个线程来打印(共享变量)
 */
public class PrintSequence {

    private static Integer flag = 1;
    private static Object lock = new Object();

     static class A implements Runnable {

        @Override
        public void run() {
            String[] strings = Help.buildNoArr(52);
            for (int i = 0; i < strings.length; i += 2) {
                synchronized (lock) {
                    while (flag != 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Help.print(strings, i, i+2);
                    flag = 2;
                    lock.notify();
                }

            }
        }
    }

     static class B implements Runnable {

        @Override
        public void run() {
            String[] strings = Help.buildCharArr(26);
            for (int i = 0; i < strings.length; i++) {
                synchronized (lock) {
                    while (flag != 2) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Help.print(strings, i, i+1);
                    flag = 1;
                    lock.notify();
                }

            }
        }
    }

    public static void main(String[] args) {
        Help.run(new A());
        Help.run(new B());
    }
}
