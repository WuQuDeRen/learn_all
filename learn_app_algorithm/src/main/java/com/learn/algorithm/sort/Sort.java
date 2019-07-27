package com.learn.algorithm.sort;

import com.google.common.collect.Lists;

import java.util.List;

public interface Sort {
    /**
     * 对集合进行排序
     * @param root
     */
    default List<Record> sort(List<Record> root) {return Lists.newArrayList();}
}
