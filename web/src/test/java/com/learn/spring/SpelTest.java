package com.learn.spring;

import lombok.Data;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.testng.collections.Lists;

import java.util.List;

@Data
class User {
    private String name;
    private Integer age;
}
public class SpelTest {

    protected ApplicationContext applicationContext = null;

    protected ExpressionParser parser = null;

    protected EvaluationContext context = null;


    @Before
    public void setUp() {
        applicationContext = new ClassPathXmlApplicationContext("classpath*:/config/spring/spring-main.xml");
        parser = new SpelExpressionParser();
        context = new StandardEvaluationContext();
    }


    @Test
    public void testSpelTemplate() {
        String spel = "我：#{name}很好看, 年龄：#{age}";

        Expression expression = parser.parseExpression(spel, new TemplateParserContext());
        User user = new User();
        user.setAge(12);
        user.setName("jifei");
        Object value = expression.getValue(user);
        System.out.println(value);

    }

    @Test
    public void testSpel() {
        String spel = "#name";
        Expression expression = parser.parseExpression(spel);
        User user = new User();
        user.setAge(12);
        user.setName("jifei");
        StandardEvaluationContext context = new StandardEvaluationContext();
//        context.setVariable("user", user);
        context.setRootObject(user);
        Object value = expression.getValue(context, String.class);
        System.out.println(value);
    }

    @Test
    public void testSpelThis() {
        String spel = "#list.?[#this.length() > 2]";
        Expression expression = parser.parseExpression(spel);
        List<String> list = Lists.newArrayList("a", "bb", "ccc", "dddd");
        context.setVariable("list", list);
        List<Integer> value = expression.getValue(context, List.class);
        System.out.println(value);
    }

    public static void main(String[] args) {
        List<Integer> result = Lists.newArrayList();
        for (int i = 0; i < 300; i++) {
            result.add(i);
        }
        System.out.println(result);
    }
}
