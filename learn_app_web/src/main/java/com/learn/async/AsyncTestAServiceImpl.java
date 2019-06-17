package com.learn.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class AsyncTestAServiceImpl implements AsyncTestAService {

    @Async
    @Override
    public Future<String> test(Integer id) throws InterruptedException {
        Thread.sleep(100);
        System.out.println("主键：" + id + " 当前线程名：" + Thread.currentThread().getName() + ", " + Thread.currentThread().getId());
        return AsyncResult.forValue("dddd");
    }
}
