package com.learn.test.dao;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicStampedReference;

@Data
class User {
    private String name;
    public volatile int old;//必须使用 volatile 标识，并且是 非 static
    public User(String name, int old) {
        this.name = name;
        this.old = old;
    }
    public String getName() {
        return name;
    }
    public int getOld() {
        return old;
    }
}

public class AtomoticTest {

    private static User user = new User("jifei", 12);

    private static AtomicStampedReference<User> atomicReference = new AtomicStampedReference(user, 0);

    private static int count = 0;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 2000; i++) {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(Integer.MAX_VALUE - 1000) % 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int stamp =  atomicReference.getStamp();
                User newUser = new User("jifei_" + Thread.currentThread().getName(), (int) Thread.currentThread().getId());
                if (atomicReference.compareAndSet(user, newUser, stamp, atomicReference.getStamp() + 1)) {
                    count++;
                    User reference = atomicReference.getReference();
                    System.out.println("线程：" + Thread.currentThread().getName() + "，newUser：" + JSON.toJSONString(newUser) + "，oldUser：" + JSON.toJSONString(user)+ "，reference"+ JSON.toJSONString(reference)+ "，count：" + count);
                }
            });
        }

    }
}
