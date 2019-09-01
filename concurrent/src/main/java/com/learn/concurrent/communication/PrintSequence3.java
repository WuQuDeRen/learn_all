package com.learn.concurrent.communication;

import lombok.Data;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Data
class Task {
    private static Lock lock = new ReentrantLock();
    private static Condition file1Con = lock.newCondition();
    private static Condition file2Con = lock.newCondition();
    private static Condition file3Con = lock.newCondition();
    private static Condition file4Con = lock.newCondition();
    private int flag;
    private String path;

    public Task(String path, int flag) {
        this.flag = flag;
        this.path = path;
    }

    public void print(String value) throws IOException {
        FileWriter writer = new FileWriter(path, true);
        writer.write(value);
        writer.flush();
        writer.close();
    }

    public void printFile1() {
        lock.lock();
        try {
            while (flag != 1) {
                file1Con.await();
            }
            flag = 2;
            print("1");
            file2Con.signal();
        } catch(Exception e) {

        } finally {
            lock.unlock();
        }
    }
    public void printFile2() {
        lock.lock();
        try {
            while (flag != 2) {
                file2Con.await();
            }
            flag = 3;
            print("2");
            file3Con.signal();
        } catch(Exception e) {

        } finally {
            lock.unlock();
        }
    }
    public void printFile3() {
        lock.lock();
        try {
            while (flag != 3) {
                file3Con.await();
            }
            flag = 4;
            print("3");
            file4Con.signal();
        } catch(Exception e) {

        } finally {
            lock.unlock();
        }
    }
    public void printFile4() {
        lock.lock();
        try {
            while (flag != 4) {
                file4Con.await();
            }
            flag = 1;
            print("4");
            file1Con.signal();
        } catch(Exception e) {

        } finally {
            lock.unlock();
        }
    }
}
public class PrintSequence3 {


    public static void main(String[] args) {
        Task task1 = new Task("C:\\Users\\Administrator\\Desktop\\tmp\\a.txt", 1);
        Task task2 = new Task("C:\\Users\\Administrator\\Desktop\\tmp\\b.txt", 2);
        Task task3 = new Task("C:\\Users\\Administrator\\Desktop\\tmp\\c.txt", 3);
        Task task4 = new Task("C:\\Users\\Administrator\\Desktop\\tmp\\d.txt", 4);
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                task1.printFile1();
                task2.printFile1();
                task3.printFile1();
                task4.printFile1();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                task1.printFile2();
                task2.printFile2();
                task3.printFile2();
                task4.printFile2();

            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                task1.printFile3();
                task2.printFile3();
                task3.printFile3();
                task4.printFile3();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                task1.printFile4();
                task2.printFile4();
                task3.printFile4();
                task4.printFile4();
            }
        }).start();
    }
}
