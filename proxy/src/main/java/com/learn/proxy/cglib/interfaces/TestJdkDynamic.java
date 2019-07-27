package com.learn.proxy.cglib.interfaces;
/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-27 15:06
 */
public class TestJdkDynamic {
    public static void main(String[] args) {
        //生成的代理类保存到磁盘
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    }
}
