package com.learn.algorithm.tree.bindary.strategy;

import com.learn.algorithm.tree.bindary.BNode;

/**
 * 定义遍历接口
 */
public interface TraversalStrategy {
    /**
     * 递归遍历
     * @param node
     */
    default void recursionTraversal(BNode node){}

    /**
     * 循环遍历，按照定义执行
     * @param node
     */
    default void loopTraversalDefinition(BNode node){}


    /**
     * 循环遍历，逆序操作，依托结构等特点
     */
    default void loopTraversalBackward(BNode node){}


    /**
     * 按照层级访问
     * @param node
     */
    default void loopTraversalHierarchy(BNode node) {}
}
