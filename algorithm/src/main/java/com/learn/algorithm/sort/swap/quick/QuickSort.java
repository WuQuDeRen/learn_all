package com.learn.algorithm.sort.swap.quick;

import com.learn.algorithm.sort.Record;
import com.learn.algorithm.sort.swap.SwapSort;

import java.util.List;

/**
 * 快速排序的基本思想：通过一趟排序将待排序记录分割成独立得两部分，其中一部分记录的关键字比另一部分记录的关键字小，然后分别对这两部分记录继续使用该方法排序，以达到整个序列有序。
 */
public class QuickSort implements SwapSort {

    @Override
    public List<Record> sort(List<Record> root) {
        quickSort(root, 0, root.size() - 1);
        return root;
    }

    // 快速排序
    private void quickSort(List<Record> subList, int low, int high) {
        if (low < high) {
            int mid =  swap(subList, low, high);
            quickSort(subList, low, mid - 1);
            quickSort(subList, mid + 1, high);
        }
    }

    // 返回 轴值 坐标
    private int swap(List<Record> subList, int low, int high) {
        Record record = subList.get(low);
        while (low < high) {
            while (high > low && record.getKeyWord() <= subList.get(high).getKeyWord()) {
                high--;
            }
            subList.set(low, subList.get(high));
            while (high > low && record.getKeyWord() >= subList.get(low).getKeyWord()) {
                low++;
            }
            subList.set(high, subList.get(low));
        }
        subList.set(low, record);
        return low;
    }


}
