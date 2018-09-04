package com.learn.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Junit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/configruation/spring/spring-main.xml", "classpath*:/configruation/spring/spring-mvc.xml"})
//public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests{
public class BaseTest {
}