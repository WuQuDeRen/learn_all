package com.learn.algorithm.tree.bindary.strategy;

import com.learn.algorithm.tree.bindary.BNode;

/**
 * 策略类上下文
 */
public class Context {

    private TraversalStrategy traversalStrategy;

    public Context() {
    }

    public Context(TraversalStrategy traversalStrategy) {
        this.traversalStrategy = traversalStrategy;
    }

    public Context(TrversalTypeEnum trversalTypeEnum) {
        this.traversalStrategy = TraversalFactory.getTraversal(trversalTypeEnum);
    }

    /**
     * 递归遍历
     * @param node
     */
    public void recursionTraversal(BNode node) {
        traversalStrategy.recursionTraversal(node);
    }

    /**
     * 循环遍历，按照定义
     * @param node
     */
    public void loopTraversalDefinition(BNode node) {
        traversalStrategy.loopTraversalDefinition(node);
    }
    /**
     * 循环遍历，逆序操作
     */
    public void loopTraversalBackward(BNode node) {
        traversalStrategy.loopTraversalBackward(node);
    }


    public void loopTraversalHierarchy(BNode node) {
        traversalStrategy.loopTraversalHierarchy(node);
    }
}
