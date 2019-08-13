package com.learn.algorithm.sort.insertion.straight;

import com.learn.algorithm.sort.Record;
import com.learn.algorithm.sort.Sort;
import com.learn.algorithm.sort.insertion.InsertionSort;

import java.util.List;

/**
 * 直接插入排序，稳定
 * 将待插入子序列元素逐步插入到有序子序列的执行过程
 */
public class StraightInsertionSort implements InsertionSort {


    public List<Record> sort(List<Record> root) {
        Record tmp = null;
        for (int i = 1, j = i - 1; i < root.size(); i++, j = i - 1) {
            tmp = root.get(i);
            while (j >= 0 && root.get(j).getKeyWord() > tmp.getKeyWord()) {
                root.set(j + 1, root.get(j));
                j--;
            }
            root.set(j + 1, tmp);
        }
        return root;
    }
}
