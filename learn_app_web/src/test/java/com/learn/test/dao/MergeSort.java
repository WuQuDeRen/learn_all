package com.learn.test.dao;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MergeSort {

    public List<Integer> sort(List<Integer> datas) {
        if (datas == null || datas.size() < 2){
            return datas;
        }
        int middle = datas.size() / 2;

        List<Integer> left = datas.subList(0, middle);

        List<Integer> right = datas.subList(middle, datas.size());

        return merge(sort(left), sort(right));
    }

    public List<Integer> merge(List<Integer> left, List<Integer> right){
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;
        for (; i < left.size() && j < right.size();) {
            Integer leftValue = left.get(i);
            Integer rightValue = right.get(j);
            if (leftValue <= rightValue) {
                result.add(leftValue);
                i++;
            } else {
                result.add(rightValue);
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

    public static void main(String[] args) {
        MergeSort sort = new MergeSort();
        List list = Lists.newArrayList(1,2,3,4,5,6,7,8);

        System.out.println("finally result => "+sort.sort(list));
    }


}
