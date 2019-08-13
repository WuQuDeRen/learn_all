package com.learn.algorithm.sort;

import com.learn.algorithm.sort.insertion.binary.BinaryIntsertionSort;
import com.learn.algorithm.sort.insertion.shell.ShellInsertionSort;
import com.learn.algorithm.sort.insertion.straight.StraightInsertionSort;
import com.learn.algorithm.sort.merge.twomerage.TwoMergeSort_Array;
import com.learn.algorithm.sort.merge.twomerage.TwoMergeSort_List;
import com.learn.algorithm.sort.selection.heap.HeapSort;
import com.learn.algorithm.sort.selection.simple.SimpleSelectionSort;
import com.learn.algorithm.sort.swap.bubble.BubbleSort;
import com.learn.algorithm.sort.swap.quick.QuickSort;

import java.util.List;

/**
 * 排序上下文
 */
public class SortContext {

    private Sort sort;

    public SortContext(SortTypeEnum sortTypeEnum) {
        switch (sortTypeEnum) {
            case INSERTION_STRAIGHT_SORT: {
                sort = new StraightInsertionSort();
                break;
            }
            case INSERTION_BINARY_SORT: {
                sort = new BinaryIntsertionSort();
                break;
            }
            case INSERTION_SHELL_SORT: {
                sort = new ShellInsertionSort();
                break;
            }
            case SWAP_BUBBLE_SORT: {
                sort = new BubbleSort();
                break;
            }
            case SWAP_QUICK_SORT: {
                sort = new QuickSort();
                break;
            }
            case SELECTION_SIMPLE__SORT: {
                sort = new SimpleSelectionSort();
                break;
            }
            case SELECTION_HEAP_SORT: {
                sort = new HeapSort();
                break;
            }
            case Merge_SORT_List: {
                sort = new TwoMergeSort_List();
            }
            case Merge_SORT_Array: {
                sort = new TwoMergeSort_Array();
            }
        }
    }


    public List<Record> sort(List<Record> root) {
        return sort.sort(root);
    }

}
