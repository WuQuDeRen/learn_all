package com.learn.algorithm.sort.selection.simple;

import com.learn.algorithm.sort.Record;
import com.learn.algorithm.sort.selection.SelectionSort;

import java.util.List;

/**
 * 简单选择排序：
 *     第一趟，从 n 个待排序记录中找出关键字最小的记录与第一个记录交换
 *     第二趟，从第二个记录开始的 (n-1) 个待排序记录中再选出关键字最小的记录与第二个记录交换
 *     如此，第 i 趟，则从第 i 个记录开始的 (n - i + 1) 个待排序记录中选出关键字最小的记录
 */
public class SimpleSelectionSort implements SelectionSort {

    @Override
    public List<Record> sort(List<Record> root) {
        for (int i = 0; i < root.size(); i++) {
            int t = i;
            for (int j = i + 1; j < root.size(); j++) {
                if (root.get(t).getKeyWord() > root.get(j).getKeyWord()) {
                    t = j;
                }
            }
            Record record = root.get(t);
            root.set(t, root.get(i));
            root.set(i, record);
        }
        return root;
    }
}
