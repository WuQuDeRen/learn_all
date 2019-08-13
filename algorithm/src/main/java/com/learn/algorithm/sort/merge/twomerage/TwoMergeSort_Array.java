package com.learn.algorithm.sort.merge.twomerage;

import com.learn.algorithm.sort.Record;
import com.learn.algorithm.sort.merge.MergeSort;

import java.util.Arrays;
import java.util.List;

/**
 * 二路归并排序：先分割成左右，然后在归并
 */
public class TwoMergeSort_Array implements MergeSort {

    public List<Record> sort(List<Record> root) {
        Record[] recordArray = mergeSort(root, 0, root.size() - 1);
        return Arrays.asList(recordArray);
    }

    private Record[] mergeSort(List<Record> root, int low, int high) {
        Record[] resultArray = new Record[root.size()];
        if (low == high) {
            resultArray[low] = root.get(low);
        }
        if (low != high) {
            int mid = (low + high) / 2;
            // 将 root【low...mid】 归并为有序的 resultListLeft【low...mid】，调用归并过程实现
            Record[] resultListLeft = mergeSort(root, low, mid);
            // 将 root【mid + 1...high】 归并为有序的 resultListLeft【mid + 1...high】，调用归并过程实现
            Record[] resultListRight = mergeSort(root, mid + 1, high);
            Record[] result = new Record[root.size()];
            for (int j = low; j <= high; j++) {
                if (j <= mid) {
                    result[j] = resultListLeft[j];
                } else {
                    result[j] = resultListRight[j];
                }
            }
            // 将有序的root【low...mid】和 有序的root【mid + 1...high】 归并为有序的 resultArray
            resultArray = merge(result, low, mid, high);
        }
        return resultArray;
    }

    // 将两个有序序列合并成一个
    private Record[] merge(Record[] root, int low, int mid, int high) {
        Record[] resultArray = new Record[root.length];
        int i = low, j = mid + 1, k = low;
        for (; i <= mid && j <= high; k++) {
            if (root[i].getKeyWord() <= root[j].getKeyWord()) {
                resultArray[k] = root[i];
                i++;
            } else {
                resultArray[k] = root[j];
                j++;
            }
        }
        while (i <= mid) {
            resultArray[k++] = root[i++];
        }
        while (j <= high) {
            resultArray[k++] = root[j++];
        }

        return resultArray;
    }



}
