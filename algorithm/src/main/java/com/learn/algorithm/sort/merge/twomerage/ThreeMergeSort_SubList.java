package com.learn.algorithm.sort.merge.twomerage;

import com.learn.algorithm.sort.Record;
import com.learn.algorithm.sort.merge.MergeSort;

import java.util.ArrayList;
import java.util.List;

/**
 * 归并排序
 */
public class ThreeMergeSort_SubList implements MergeSort {


    public List<Record> sort(List<Record> root) {
        List<Record> recordArray = mergeSort(root);
        return recordArray;
    }

    // 分隔
    public List<Record> mergeSort(List<Record> root) {
        if (root == null || root.size() < 2) {
            return root;
        }
        int middle = root.size() / 2;

        List<Record> left = root.subList(0, middle);
        List<Record> right = root.subList(middle, root.size());

        return merge(sort(left), sort(right));
    }

    // 归并过程
    public List<Record> merge(List<Record> left, List<Record> right){
        List<Record> result = new ArrayList<>();
        int i = 0, j = 0;
        for (; i < left.size() && j < right.size();) {
            if (left.get(i).getKeyWord() <= right.get(j).getKeyWord()) {
                result.add(left.get(i));
                i++;
            } else {
                result.add(right.get(j));
                j++;
            }
        }
        while (i < left.size()){
            result.add(left.get(i++));
        }
        while (j < left.size()){
            result.add(right.get(j++));
        }
        System.out.println("return data:"+result);
        return result;
    }

}
