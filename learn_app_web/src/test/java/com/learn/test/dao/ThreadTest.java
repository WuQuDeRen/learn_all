package com.learn.test.dao;




import com.learn.tmp.Order;

import java.util.concurrent.*;

class Task implements Callable<Order> {

    @Override
    public Order call() throws Exception {
        int i = 0;
        while (true) {
            System.out.println("线程号：" + Thread.currentThread().getId());
            TimeUnit.MILLISECONDS.sleep(3);
            if (i > 1000000) {
                return new Order("1111", "order");
            }
        }
    }
}
public class ThreadTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Order> future = executor.submit(new Task());
        long start = System.currentTimeMillis();
        Order order = future.get(2, TimeUnit.SECONDS);

        System.out.println(order + "，消耗：" + (System.currentTimeMillis()  - start));

    }
}
