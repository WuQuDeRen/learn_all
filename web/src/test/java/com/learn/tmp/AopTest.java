package com.learn.tmp;

import com.learn.app.core.service.PersonService;
import com.learn.async.AsyncTestAService;
import com.learn.app.core.service.ReceiveModel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AopTest {

    private static Logger logger = LoggerFactory.getLogger(AopTest.class);

    private ApplicationContext applicationContext = null;

    //@Before
    public void setUp() {
        MDC.put("", "");
        applicationContext = new ClassPathXmlApplicationContext("classpath*:/config/spring/spring-main.xml");
    }

    @Test
    public void testGetA() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:/config/spring/spring-main.xml");
        PersonService personService = applicationContext.getBean(PersonService.class);
        personService.getA();
    }

    // 测试异常
    @Test
    public void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:/config/spring/spring-main.xml");
        PersonService personService = applicationContext.getBean(PersonService.class);
        personService.exception("jifei", 12, new ReceiveModel("fj", "324"));
    }

    @Test
    public void testAsync() throws InterruptedException {
        AsyncTestAService asyncTestAService = applicationContext.getBean(AsyncTestAService.class);
        asyncTestAService.test(1);
        System.out.println(1);
        asyncTestAService.test(2);
        System.out.println(2);
        Thread.sleep(10000);
        System.out.println(3);
    }

    public static void main(String[] args) {
        MDC.put("traceId", UUID.randomUUID().toString());
        Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();
        logger.info("desc => start");
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(() -> {
            MDC.setContextMap(copyOfContextMap);
            while (true) {
                try {
                    Thread.sleep(10);
                    logger.info("desc => {}", Thread.currentThread().getId());
                } catch (Exception e) {

                }
            }

        });
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("desc => end");
    }

}
