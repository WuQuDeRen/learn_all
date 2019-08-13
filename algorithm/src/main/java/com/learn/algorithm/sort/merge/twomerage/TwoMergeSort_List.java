package com.learn.algorithm.sort.merge.twomerage;

import com.google.common.collect.Lists;
import com.learn.algorithm.sort.Record;
import com.learn.algorithm.sort.merge.MergeSort;

import java.util.Arrays;
import java.util.List;

/**
 * 二路归并排序
 */
public class TwoMergeSort_List implements MergeSort {

    public List<Record> sort(List<Record> root) {
        mergeSort(root, root, 0, root.size() - 1);
        return root;
    }

    private void mergeSort(List<Record> root, List<Record> resultList, int low, int high) {
        List<Record> tmpList = Lists.newArrayList(root);
        if (low == high) {
            // 这行代码可以不要，因为这边对于每一个下标只有走一次
//            resultList.set(low, root.get(low));
        } else {
            int mid = (low + high) / 2;
            mergeSort(root, tmpList, low, mid);
            mergeSort(root, tmpList, mid + 1, high);
            merge(tmpList, resultList, low, mid, high);
        }
    }

    private void merge(List<Record> root, List<Record> resultList, int low, int mid, int high) {
        int i = low, j = mid + 1, k = low;
        for (; i <= mid && j <= high; k++) {
            if (root.get(i).getKeyWord() <= root.get(j).getKeyWord()) {
                resultList.set(k, root.get(i));
                i++;
            } else {
                resultList.set(k, root.get(j));
                j++;
            }
        }
        while (i <= mid) {
            resultList.set(k++, root.get(i++));
        }
        while (j <= high) {
            resultList.set(k++, root.get(j++));
        }
    }

}
