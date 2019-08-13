package com.learn.algorithm.tree.bindary.strategy;

import com.learn.algorithm.tree.bindary.BNode;
import com.learn.algorithm.tree.bindary.PrintUtil;

import java.util.Stack;

/**
 * 中序遍历二叉树
 */
public class MiddleOrderSearch implements TraversalStrategy {

    @Override
    public void recursionTraversal(BNode node) {
        if (node != null) {
            recursionTraversal(node.getLChild());
            PrintUtil.visitRecrusion(node);
            recursionTraversal(node.getRChild());
        }
    }

    /**
     * 循环遍历，按照定义执行      LTR
     * @param node
     */
    @Override
    public void loopTraversalDefinition(BNode node) {
        Stack<BNode> stackNode = new Stack<BNode>();
        BNode p = node;
        while (p != null || !stackNode.isEmpty()) {
            if (p != null) {
                stackNode.push(p);
                p = p.getLChild();
            } else {
               p = stackNode.pop();
               PrintUtil.visitLoop(p);
               p = p.getRChild();
            }
        }
    }

    /**
     * 循环遍历，逆序操作      LRT   的逆序     TRL
     * @param node
     */
    @Override
    public void loopTraversalBackward(BNode node) {
        Stack<BNode> stackNode = new Stack<BNode>();
        Stack<BNode> stackPost = new Stack<BNode>();
        BNode p = node;
        while (p != null || !stackNode.isEmpty()) {
            if (p != null) {
                stackNode.push(p);
                p = p.getRChild();
            } else {
                p = stackNode.pop();
                stackPost.push(p);
                p = p.getLChild();
            }
        }
        while (!stackPost.isEmpty()) {
            p = stackPost.pop();
            PrintUtil.visitLoop(p);
        }
    }


}
