package com.learn.algorithm.sort;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class SortTest {
    List<Record> root = Lists.newArrayList();

    @Before
    public void setUp() {
        // 集合初始化
        Record a = new Record(27);
        Record b = new Record(19932);
        Record c = new Record(32);
        Record d = new Record(13);
        Record e = new Record(3239438);
        Record f = new Record(1);
        root.add(a);
        root.add(b);
        root.add(c);
        root.add(d);
        root.add(e);
        root.add(f);
    }

    // 1、直接插入排序
    @Test
    public void testStraightInsertionSort() {
        SortContext sort = new SortContext(SortTypeEnum.INSERTION_STRAIGHT_SORT);
        List<Record> sortResult = sort.sort(root);
        System.out.println(sortResult);
    }

    // 2、折半插入排序
    @Test
    public void testBinaryInsertionSort() {
        SortContext sort = new SortContext(SortTypeEnum.INSERTION_BINARY_SORT);
        List<Record> sortResult = sort.sort(root);
        System.out.println(sortResult);
    }

    // 3、希尔排序
    @Test
    public void testShellInsertionSort() {
        SortContext sort = new SortContext(SortTypeEnum.INSERTION_SHELL_SORT);
        List<Record> sortResult = sort.sort(root);
        System.out.println(sortResult);
    }


    // 4、冒泡排序
    @Test
    public void testBubbleSwapSort() {
        SortContext sort = new SortContext(SortTypeEnum.SWAP_BUBBLE_SORT);
        List<Record> sortResult = sort.sort(root);
        System.out.println(sortResult);
    }

    // 5、快速排序
    @Test
    public void testQuickSwapSort() {
        SortContext sort = new SortContext(SortTypeEnum.SWAP_QUICK_SORT);
        List<Record> sortResult = sort.sort(root);
        System.out.println(sortResult);
    }

    // 6、简单选择排序
    @Test
    public void testSimpleSelection() {
        SortContext sort = new SortContext(SortTypeEnum.SELECTION_SIMPLE__SORT);
        List<Record> sortResult = sort.sort(root);
        System.out.println(sortResult);
    }

    // 7、堆排序
    @Test
    public void testHeapSelectionSort() {
        SortContext sort = new SortContext(SortTypeEnum.SELECTION_HEAP_SORT);
        List<Record> sortResult = sort.sort(root);
        System.out.println(sortResult);
    }


    // 8、归并排序
    @Test
    public void testMergeSort_List() {
        SortContext sort = new SortContext(SortTypeEnum.Merge_SORT_List);
        List<Record> sortResult = sort.sort(root);
        System.out.println(sortResult);
    }

    // 9、归并排序
    @Test
    public void testMergeSort_Array() {
        SortContext sort = new SortContext(SortTypeEnum.Merge_SORT_List);
        List<Record> sortResult = sort.sort(root);
        System.out.println(sortResult);
    }
}
