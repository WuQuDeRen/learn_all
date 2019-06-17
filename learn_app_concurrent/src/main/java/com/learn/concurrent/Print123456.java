package com.learn.concurrent;

/**
 * 启动3个线程打印递增的数字, 线程1先打印1,2,3,4,5, 然后是线程2打印6,7,8,9,10, 然后是线程3打印11,12,13,14,15. 接着再由线程1打印16,17,18,19,20….以此类推, 直到打印到75.
 */
public class Print123456 implements Runnable{
    private String name;
    private Object prev;
    private Object self;
    private int count;
    public Print123456(String name,Object prev,Object self,int count){
        this.name=name;
        this.prev=prev;
        this.self=self;
        this.count=count;
    }
    @Override
    public void run() {
        while(count<=75){
            synchronized (prev){
                synchronized (self){
                    for (int i=0;i<5;i++){
                        count=count+1;
                        if(count>75)break;
                        System.out.println(name+": "+count);

                    }
                    count=count+10;
                    //释放自身锁，并唤醒在等待该锁的线程
                    self.notify();

                }
                try {
                    //让当前线程进入通知等待队列，释放前一个锁
                    prev.wait();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //System.out.println("两个锁全部释放，重新进入新的循环，但是由于有prev.wait()所以该线程等待获取prev的锁");
        }
    }
}
 class Main {
    public static void main(String[] args) throws InterruptedException {
        Object a=new Object();
        Object b=new Object();
        Object c=new Object();
        Print123456 A=new Print123456("线程1",c,a,0);
        Print123456 B=new Print123456("线程2",a,b,5);
        Print123456 C=new Print123456("线程3",b,c,10);

        new Thread(A).start();
        Thread.sleep(100);
        new Thread(B).start();
        Thread.sleep(100);
        new Thread(C).start();
        Thread.sleep(100);

    }
}
