package com.learn.async;

import java.util.concurrent.Future;

public interface AsyncTestAService {

    Future<String> test(Integer id) throws InterruptedException;
}
