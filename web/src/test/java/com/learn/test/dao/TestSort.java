package com.learn.test.dao;

import java.util.List;

public class TestSort {

    public void adjustHeap(List<Integer> root, int low, int high) {
       for (int i = low, j = i * 2; i <= high; i = 2 * i) {
           if (root.get(i * 2) < root.get(j)) {

           }
       }
    }

    public static void main(String[] args) {

    }
}
