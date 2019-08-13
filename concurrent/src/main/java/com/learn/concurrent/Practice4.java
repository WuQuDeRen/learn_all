package com.learn.concurrent;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 四个线程t1,t2,t3,t4,向4个文件中写入数据，t1只能写入1，t2只能写入2，t3只能写入3，t4只能写入4，对4个文件A，B，C，D写入如下内容:
 * A:123412341234.....
 * B:234123412341....
 * C:341234123412....
 * D:412341234123....
 * 怎么实现同步可以让线程并行工作？
 * @author luao
 * @description 创建三个线程，分别打印abc，循环10次打出abc...
 * @date 2019-07-15 15:38
 */
public class Practice4 {

    public static void main(String[] args) {
        final MyTask t1 = new MyTask(new File("/Users/ji_fei/Desktop/thread/A.txt"),1);
        final MyTask t2 = new MyTask(new File("/Users/ji_fei/Desktop/thread/B.txt"),2);
        final MyTask t3 = new MyTask(new File("/Users/ji_fei/Desktop/thread/C.txt"),3);
        final MyTask t4 = new MyTask(new File("/Users/ji_fei/Desktop/thread/D.txt"),4);
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        t1.f1();
                        t2.f1();
                        t3.f1();
                        t4.f1();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        t1.f2();
                        t2.f2();
                        t3.f2();
                        t4.f2();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        t1.f3();
                        t2.f3();
                        t3.f3();
                        t4.f3();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        t1.f4();
                        t2.f4();
                        t3.f4();
                        t4.f4();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }



}

class MyTask {

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private Condition condition4 = lock.newCondition();
    private int flag;
    private File file;


    public MyTask(File file,int flag) {
        this.flag = flag;
        this.file = file;
    }

    void f1() throws InterruptedException {
        lock.lockInterruptibly();
        try{
            while (flag != 1){
                condition1.await();
            }
//            System.out.print("1");
            try {
                writeFile("1");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            flag = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void f2() throws InterruptedException {
        lock.lockInterruptibly();
        try{
            while (flag != 2){
                condition2.await();
            }
//            System.out.print("2");
            try {
                writeFile("2");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            flag = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void f3() throws InterruptedException {
        lock.lockInterruptibly();
        try{
            while (flag != 3){
                condition3.await();
            }
//            System.out.print("3");
            try {
                writeFile("3");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            flag = 4;
            condition4.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void f4() throws InterruptedException {
        lock.lockInterruptibly();
        try{
            while (flag != 4){
                condition4.await();
            }
//            System.out.print("4");
            try {
                writeFile("4");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            flag = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void writeFile(String s) throws FileNotFoundException {
        FileOutputStream out=null;
        OutputStreamWriter cout=null;
        try{
            lock.lockInterruptibly();
            out=new FileOutputStream(file,true);
            cout=new OutputStreamWriter(out);
            cout.write(s);
        }catch(InterruptedException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                cout.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            lock.unlock();
        }
    }


    public String readFile(){
        FileInputStream in=null;
        InputStreamReader cin=null;
        BufferedReader br=null;
        char[] cbuf=new char[100];
        try {
            in=new FileInputStream(file);
            cin=new InputStreamReader(in);
            br=new BufferedReader(cin);
            StringBuilder sb=new StringBuilder();
            int temp=0;
            while((temp=br.read(cbuf))>0){
                sb.append(new String(cbuf),0,temp);
                Arrays.fill(cbuf,' ');
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally{
            try {
                in.close();
                cin.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}



