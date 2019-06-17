package com.learn.algorithm.tree.bindary.strategy;

import com.learn.algorithm.tree.bindary.BNode;
import com.learn.algorithm.tree.bindary.PrintUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 按照层级访问
 */
public class HierarchySearch implements TraversalStrategy {


    /**
     * 按照层级访问
     * @param node
     */
    @Override
    public void loopTraversalHierarchy(BNode node) {
         Queue<BNode> queueNode = new LinkedList();
         queueNode.offer(node);
         while (!queueNode.isEmpty()) {
             BNode p = queueNode.poll();
             if (p != null) {
                 PrintUtil.visitLoop(p);
                 if (p.getLChild() != null) {
                     queueNode.offer(p.getLChild());
                 }
                 if (p.getRChild() != null) {
                     queueNode.offer(p.getRChild());
                 }
             }
         }
    }


}
