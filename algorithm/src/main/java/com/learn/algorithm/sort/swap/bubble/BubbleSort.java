package com.learn.algorithm.sort.swap.bubble;

import com.learn.algorithm.sort.Record;
import com.learn.algorithm.sort.swap.SwapSort;

import java.util.List;

/**
 * 冒泡排序：不停地的比较 相邻 记录的关键字，如果不满足排序要求，就交换相邻记录，直到所有的记录都已经排好序为止。
 */
public class BubbleSort implements SwapSort {


    public List<Record> sort(List<Record> root) {
        for (int i = 1; i <= root.size(); i++) {    // 实际进行 （n - 1) 趟排序
            for (int j = 1; j < root.size() - i + 1; j++) {     // 第 i 趟，进行 n - i 比较
                if (root.get(j - 1).getKeyWord() >= root.get(j).getKeyWord()) {
                    Record record = root.get(j);
                    root.set(j, root.get(j - 1));
                    root.set(j - 1, record);
                }
            }
        }
        return root;
    }
}
