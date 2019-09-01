package com.learn.concurrent.communication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Help {
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static String[] buildNoArr(int max) {
        String[] noArr = new String[max];
        for(int i=0;i<max;i++){
            noArr[i] = Integer.toString(i+1);
        }
        return noArr;
    }

    public static String[] buildCharArr(int max) {
        String[] charArr = new String[max];
        int tmp = 65;
        for(int i=0;i<max;i++){
            charArr[i] = String.valueOf((char)(tmp+i));
        }
        return charArr;
    }

    public static void run(Runnable runnable) {
        threadPool.submit(runnable);
    }

    public static void print(String[] arr, int begin, int end) {
        for (int i = begin; i < end; i++) {
            System.out.print(arr[i]);
        }
    }
}
