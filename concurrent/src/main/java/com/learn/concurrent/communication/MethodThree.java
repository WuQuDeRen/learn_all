package com.learn.concurrent.communication;

public class MethodThree {
    private volatile ThreadToGo threadToGo = new ThreadToGo();
    class ThreadToGo {
        int value = 1;
    }
    public Runnable newThreadOne() {
        final String[] inputArr = Help.buildNoArr(52);
        return new Runnable() {
            private String[] arr = inputArr;
            public void run() {
                for (int i = 0; i < arr.length; i=i+2) {
                    while(threadToGo.value != 1){}
                    Help.print(arr, i, i + 2);
                    threadToGo.value = 2;
                }
            }
        };
    }
    public Runnable newThreadTwo() {
        final String[] inputArr = Help.buildCharArr(26);
        return new Runnable() {
            private String[] arr = inputArr;
            public void run() {
                for (int i = 0; i < arr.length; i++) {
                    while(threadToGo.value != 2){}
                    Help.print(arr, i, i+1);
                    threadToGo.value = 1;
                }
            }
        };
    }
    public static void main(String args[]) throws InterruptedException {
        MethodThree three = new MethodThree();
        Help.run(three.newThreadOne());
        Help.run(three.newThreadTwo());
    }
}