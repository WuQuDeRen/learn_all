package com.learn.algorithm.sort;

public enum SortTypeEnum {
    INSERTION_STRAIGHT_SORT(1,"直接插入排序"), INSERTION_BINARY_SORT(2,"折半插入排序"), INSERTION_SHELL_SORT(3,"希尔排序"),
    SWAP_BUBBLE_SORT(4,"冒泡排序"), SWAP_QUICK_SORT(5,"快速排序"),
    SELECTION_SIMPLE__SORT(6,"简单选择"), SELECTION_HEAP_SORT(7,"堆排序"),
    Merge_SORT_List(8,"归并排序_集合实现"), Merge_SORT_Array(9, "归并排序_数组实现");


    private String desc;

    private Integer id;

     SortTypeEnum(Integer id, String desc) {
        this.desc = desc;
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
