package com.learn.algorithm.search.tree;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = {"lChild", "rChild"})
public class BinaryTree {

    private Integer data;

    private BinaryTree lChild;

    private BinaryTree rChild;

    public BinaryTree() {
    }

    public BinaryTree(Integer data, BinaryTree lChild, BinaryTree rChild) {
        this.data = data;
        this.lChild = lChild;
        this.rChild = rChild;
    }


}
