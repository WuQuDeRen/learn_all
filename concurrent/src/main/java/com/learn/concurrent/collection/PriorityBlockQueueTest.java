package com.learn.concurrent.collection;

import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockQueueTest {

    public static void main(String[] args) {
        PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<>();
        queue.put("h");
        queue.put("g");
        queue.put("f");
        queue.put("e");
        queue.put("d");
        queue.put("c");
        queue.put("b");
        queue.put("a");
        for (String item : queue) {
            System.out.print(item + " ");
        }
        System.out.println();
        TreeSet<String> treeSet = new TreeSet<>();

        treeSet.add("h");
        treeSet.add("f");
        treeSet.add("g");
        treeSet.add("e");
        treeSet.add("d");
        treeSet.add("c");
        treeSet.add("b");
        treeSet.add("a");

        for (String item : treeSet) {
            System.out.print(item + " ");
        }


        PriorityQueue<String> queue2 = new PriorityQueue<>();


        queue2.offer("f");
        queue2.offer("e");
        queue2.offer("d");
        queue2.offer("c");
        queue2.offer("b");
        queue2.offer("a");
        queue2.offer("g");
        System.out.println("--------------------------------------");

        while (!queue2.isEmpty()) {
            String poll = queue2.poll();
            System.out.print(poll + " ");
        }


    }
}
