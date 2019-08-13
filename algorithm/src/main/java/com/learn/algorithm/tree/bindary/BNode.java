package com.learn.algorithm.tree.bindary;

import com.learn.algorithm.tree.DataType;
import lombok.Data;

@Data
public class BNode {
    // 数据
    private DataType data;
    // 指向左
    private BNode lChild;
    // 指向右
    private BNode rChild;
    // 指向双亲
    private BNode parent;
    // 标志（用于后序遍历）
    private int flag = 0;

    public BNode() {}

    public BNode(String name) {
        this.data = new DataType(name);
    }
}
