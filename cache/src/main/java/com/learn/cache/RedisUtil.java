package com.learn.cache;

import redis.clients.jedis.*;

import java.util.List;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-13 21:42
 */
public class RedisUtil {


    private static JedisPool connPool;

    static {
        String localhost = "127.0.0.1";

        JedisPoolConfig config = new JedisPoolConfig();
        // 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；

        // 如果赋值为-1，则表示不限制；如果pool已经分配了maxTotal个jedis实例，则此时pool的状态为exhausted(耗尽）.
        config.setMaxTotal(500);

        // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例
        config.setMaxIdle(5);

        // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
        config.setMaxWaitMillis(1000 * 10);

        // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        config.setTestOnBorrow(true);

        connPool = new JedisPool(config, localhost, 6379, 10000, "test123");
        //10000是 protocol.timeout 默认值2000

    }

    public static void main(String[] args) {
        //
        Jedis client = connPool.getResource();
        String aStr = client.get("a");
        System.out.println(aStr);
        //
        client.sadd("set_1", "hhh", "222", "h_3__hh");
        ScanParams params = new ScanParams();
        params.match("h*");
        ScanResult<String> set_1 = client.sscan("set_1", "0", params);
        List<String> result = set_1.getResult();
        System.out.println(result);
    }
}
