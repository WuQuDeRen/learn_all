package com.learn.algorithm.sort.selection.heap;

import com.google.common.collect.Lists;
import com.learn.algorithm.sort.Record;
import com.learn.algorithm.sort.selection.SelectionSort;

import java.util.List;

/**
 * 堆排序
 */
public class HeapSort implements SelectionSort {

    @Override
    public List<Record> sort(List<Record> root) {
        for (int i = root.size() / 2; i > 0; i--) {
            heapSortAdjust(root, i, root.size());
        }
        List<Record> result = Lists.newArrayList();
        for (int i = root.size(); i > 1; i--) {
            Record tmp = root.get(0);
            root.set(0, root.get(i - 1));
            root.set(i - 1, tmp);
            result.add(heapSortAdjust(root, 1, i - 1));
        }
        return result;
    }

    private Record heapSortAdjust(List<Record> root, int low, int high) {
        int i = low;
        for (int j = 2 * i; j <= high; j = 2 * j) {
            if (j < high && root.get(j - 1).getKeyWord() < root.get(j).getKeyWord()) {
                j = j + 1;
            }
            if (root.get(i - 1).getKeyWord() <= root.get(j - 1).getKeyWord()) {
                Record record = root.get(i - 1);
                root.set(i - 1, root.get(j - 1));
                root.set(j - 1, record);
                i = j;
            } else {
                break;
            }
        }
        return root.get(0);
    }
}
