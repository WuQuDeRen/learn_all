package com.learn.concurrent;

import java.io.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author luao
 * @description 四个线程t1, t2, t3, t4, 向4个文件中写入数据，t1只能写入1，t2只能写入2，t3只能写入3，t4只能写入4，对4个文件A，B，C，D写入如下内容:
 * A:123412341234.....
 * B:234123412341....
 * C:341234123412....
 * D:412341234123....
 * 怎么实现同步可以让线程并行工作？
 * @date 2019-07-15 15:38
 */
public class Practice5 {


    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch cdl = new CountDownLatch(4);

        final MyTask2 t1 = new MyTask2(new File("../A.txt"),1);
        final MyTask2 t2 = new MyTask2(new File("../B.txt"),2);
        final MyTask2 t3 = new MyTask2(new File("../C.txt"),3);
        final MyTask2 t4 = new MyTask2(new File("../D.txt"),4);
        int count = 10;
        initThreads(cdl, t1, t2, t3, t4, count);
        cdl.await();
        System.out.println("A文件："+t1.readFile());
        System.out.println("B文件："+t2.readFile());
        System.out.println("C文件："+t3.readFile());
        System.out.println("D文件："+t4.readFile());
        t1.deleteFile();
        t2.deleteFile();
        t3.deleteFile();
        t4.deleteFile();
    }

    private static void initThreads(CountDownLatch cdl, MyTask2 t1, MyTask2 t2, MyTask2 t3, MyTask2 t4, int count) {
        ExecutorService ex= Executors.newFixedThreadPool(4);
        ex.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++) {
                    try {
                        t1.f1();
                        t2.f1();
                        t3.f1();
                        t4.f1();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                cdl.countDown();
            }
        });
        ex.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++) {
                    try {
                        t1.f2();
                        t2.f2();
                        t3.f2();
                        t4.f2();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                cdl.countDown();
            }
        });
        ex.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++) {
                    try {
                        t1.f3();
                        t2.f3();
                        t3.f3();
                        t4.f3();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                cdl.countDown();
            }
        });
        ex.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++) {
                    try {
                        t1.f4();
                        t2.f4();
                        t3.f4();
                        t4.f4();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                cdl.countDown();
            }
        });
    }


}

class MyTask2 {

    private int flag;
    private File file;


    public MyTask2(File file,int flag) {
        this.flag = flag;
        this.file = file;
    }

    synchronized void f1() throws InterruptedException {

//        System.out.println("f1() 进入临界区（file："+file.getName()+"|flag："+flag+"）");
        try{
            while (flag != 1){
//                System.out.println("f1() 被阻塞（file："+file.getName()+"|flag："+flag+"）");
                this.wait();
            }
//            System.out.println("f1() 被唤醒（file："+file.getName()+"|flag："+flag+"）");
            try {
                writeFile("1");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            flag = 2;
//            System.out.println("f1() 唤醒f2()（file："+file.getName()+"|flag："+flag+"）");

            this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }

    synchronized void f2() throws InterruptedException {
//        System.out.println("f2() 进入临界区（file："+file.getName()+"|flag："+flag+"）");
        try{
            while (flag != 2){
//                System.out.println("f2() 被阻塞（file："+file.getName()+"|flag："+flag+"）");
                this.wait();
            }
//            System.out.print("2");
//            System.out.println("f2() 被唤醒（file："+file.getName()+"|flag："+flag+"）");
            try {
                writeFile("2");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            flag = 3;
            this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }

    synchronized void f3() throws InterruptedException {
//        System.out.println("f3() 进入临界区（file："+file.getName()+"|flag："+flag+"）");
        try{
            while (flag != 3){
//                System.out.println("f3() 被阻塞（file："+file.getName()+"|flag："+flag+"）");
                this.wait();
            }
//            System.out.print("3");
//            System.out.println("f3() 被唤醒（file："+file.getName()+"|flag："+flag+"）");
            try {
                writeFile("3");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            flag = 4;
            this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }

    synchronized void f4() throws InterruptedException {
//        System.out.println("f4() 进入临界区（file："+file.getName()+"|flag："+flag+"）");

        try{
            while (flag != 4){
//                System.out.println("f4() 被阻塞（file："+file.getName()+"|flag："+flag+"）");
                this.wait();
            }
//            System.out.print("4");
//            System.out.println("f4() 被唤醒（file："+file.getName()+"|flag："+flag+"）");
            try {
                writeFile("4");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            flag = 1;
            this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }

    public synchronized void writeFile(String s) throws FileNotFoundException {
        FileOutputStream out=null;
        OutputStreamWriter cout=null;
        try{
            out=new FileOutputStream(file,true);
            cout=new OutputStreamWriter(out);
            cout.write(s);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                cout.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
//                Arrays.fill(cbuf,' ');
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

    public void deleteFile(){
        file.delete();
    }

}