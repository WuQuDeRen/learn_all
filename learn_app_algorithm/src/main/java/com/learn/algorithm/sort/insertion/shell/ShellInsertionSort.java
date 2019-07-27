package com.learn.algorithm.sort.insertion.shell;

import com.google.common.collect.Lists;
import com.learn.algorithm.sort.Record;
import com.learn.algorithm.sort.Sort;
import com.learn.algorithm.sort.insertion.InsertionSort;

import java.util.List;

/**
 * 希尔排序  不稳定
 * 基本思想：先将待排序列分割成若干个子序列分别进行直接插入排序，待整个序列基本有序时，再对全体记录进行一次直接插入排序
 */
public class ShellInsertionSort implements InsertionSort {

    @Override
    public List<Record> sort(List<Record> root) {
        List<Integer> gaps = Lists.newArrayList();
        for (int i = root.size() / 2; i >= 1; i = i / 2) {
            gaps.add(i);
        }
        for (int i = 0; i < gaps.size(); i++) {
            shellSort(root, gaps.get(i));
        }
        return root;
    }

    /**
     * 注意步长
     * @param root
     * @param gap
     */
    public void shellSort(List<Record> root, int gap) {
        for (int i = gap; i < root.size(); i++) {
            Record tmp = root.get(i);
            if (root.get(i - gap).getKeyWord() > tmp.getKeyWord()) {
                int j = i - gap;
                for (;j >= 0 && tmp.getKeyWord() < root.get(j).getKeyWord(); j = j - gap) {
                        root.set(j + gap, root.get(j));
                }
                root.set(j + gap, tmp);
            }
        }
    }
}
