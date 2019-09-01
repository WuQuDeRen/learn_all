package com.learn.system.util;

import com.google.common.collect.Lists;
import org.apache.poi.ss.formula.functions.Count;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;

public class AsyncUtil<T> {

    private static final Logger logger = LoggerFactory.getLogger(AsyncUtil.class);

    /**
     * 针对第三方未提供批量查询接口而设计的方法，提高查询效率
     * @param rpc
     * @param rpcParam
     * @param <S>  请求第三方接口入参的数据类型
     * @param <T>  第三方接口的返回值类型
     * @param <T>  总数据量
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static <S, T> List<T> getResult(Function<List<S>, List<T>> rpc, List<S> rpcParamList) throws InterruptedException, ExecutionException {
        Integer sourceListSize = rpcParamList.size();
        CountDownLatch countDownLatch = new CountDownLatch(sourceListSize);
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        ExecutorService executorService2 = Executors.newFixedThreadPool(3);

        int pageSize = 3;
        int totalSize = sourceListSize % pageSize == 0 ? sourceListSize / pageSize : (sourceListSize / pageSize + 1);

        List<Future<List<T>>> futureList = Lists.newArrayList();
        // 1、启动线程执行任务 去请求第二、三方接口
        for (int pageNo = 0; pageNo < totalSize; pageNo++) {
            int begin = pageNo * pageSize;
            int end = (pageNo + 1) * pageSize;
            Future<List<T>> future = executorService.submit(() -> {
                    List<T> result = null;
                    try {
                        List<S> rpcParamSub = rpcParamList.subList(begin, end);
                        // 调用第二、三方接口
                        result = rpc.apply(rpcParamSub);
                    } catch (Exception e) {
                        logger.error("调用第二、三方库异常", e);
                    }
                    return result;
            });
            futureList.add(future);
        }
        List<Future<List<T>>> resultFutureList = Lists.newArrayList();
        // 2、启动线程执行任务 获取结果进行组装
        for (Future<List<T>> future : futureList) {
            Future<List<T>> futureResult = executorService2.submit(() -> {
                List<T> result = future.get();
                // 计数器减1
                countDownLatch.countDown();
                return result;
            });
            resultFutureList.add(futureResult);
        }
        countDownLatch.await();
        List<T> resultList = Lists.newArrayList();
        for (Future<List<T>> future : resultFutureList) {
            List<T> result = future.get();
            resultList.addAll(result);
        }
        return resultList;
    }

    public static void main(String[] args) {
        List<String> sourceList = Lists.newArrayList();

    }
}
class FileTask {

    public static String reader(List<String> path) throws FileNotFoundException {
        //FileReader fileReader = new FileReader(path);
        return null;
    }

}
