package com.learn.algorithm.sort.insertion.binary;

import com.learn.algorithm.sort.Record;
import com.learn.algorithm.sort.insertion.InsertionSort;

import java.util.List;

/**
 * 折半插入排序 稳定
 */
public class BinaryIntsertionSort implements InsertionSort {

    @Override
    public List<Record> sort(List<Record> root) {
        Record tmp = null;
        for (int i = 1; i < root.size(); i++) {
            tmp = root.get(i);
            int low = 0;
            int high = i - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (tmp.getKeyWord() >= root.get(mid).getKeyWord()) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            for (int j = i - 1; j >= low; j--) {
                root.set(j + 1, root.get(j));
            }
            root.set(low, tmp);
        }
        return root;
    }
}
