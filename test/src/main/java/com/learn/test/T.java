package com.learn.test;

import com.learn.system.exception.ServerException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-08-02 11:52
 */
public class T {

    public void test() {
        throw new ServerException("dddd", "ddd");
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = T.class.getMethod("test");
        T t = new T();
        method.invoke(null);
    }
}
