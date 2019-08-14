package com.learn.algorithm.tmp;

import lombok.Data;

@Data
public class BinSortRecord {
    private Integer key;
    private BinSortRecord lChild;
    private BinSortRecord rChild;

    public BinSortRecord(Integer key) {
        this.key = key;
    }
}
