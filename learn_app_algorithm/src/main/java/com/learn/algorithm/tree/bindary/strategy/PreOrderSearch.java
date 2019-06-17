package com.learn.algorithm.tree.bindary.strategy;

import com.learn.algorithm.tree.bindary.BNode;
import com.learn.algorithm.tree.bindary.PrintUtil;

import java.util.Stack;

/**
 * 先序遍历二叉树
 */
public class PreOrderSearch implements TraversalStrategy {
    /**
     * 递归遍历
     * @param node
     */
    public void recursionTraversal(BNode node) {
        if (node != null) {
            PrintUtil.visitRecrusion(node);
            recursionTraversal(node.getLChild());
            recursionTraversal(node.getRChild());
        }
    }


    /**
     * 循环遍历，按照定义执行      TLR
     * @param node
     */
    @Override
    public void loopTraversalDefinition(BNode node) {
        BNode p = node;
        Stack<BNode> stack = new Stack<BNode>();
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                PrintUtil.visitLoop(p);
                stack.push(p);
                p = p.getLChild();
            } else {
                p = stack.pop();
                p = p.getRChild();
            }
        }
    }


    /**
     * 循环遍历，逆序操作      LRT   的逆序     TRL实现（TRL的定义）
     * 这个实现 要点是 两次入栈，两次出栈
     * @param node
     */
    @Override
    public void loopTraversalBackward(BNode node) {
        Stack<BNode> stackNode = new Stack<BNode>();
        Stack<BNode> stackPre = new Stack<BNode>();
        BNode p = node;
        while (p != null || !stackNode.isEmpty()) {
            if (p != null) {
                p.setFlag(0);
                stackNode.push(p);  // 第一次入栈
                p = p.getRChild();
            } else {
                p = stackNode.pop();
                if (p.getFlag() == 0) {
                    p.setFlag(1);
                    stackNode.push(p);  // 第二次入栈
                    p = p.getLChild();
                } else {
                    stackPre.push(p);
                    // 为了出栈
                    p = null;
                }
            }
        }
        while (!stackPre.isEmpty()) {
            p = stackPre.pop();
            PrintUtil.visitLoop(p);
        }
    }


}
