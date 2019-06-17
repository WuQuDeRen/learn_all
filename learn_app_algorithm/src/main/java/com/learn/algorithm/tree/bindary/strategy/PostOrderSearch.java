package com.learn.algorithm.tree.bindary.strategy;

import com.learn.algorithm.tree.bindary.BNode;
import com.learn.algorithm.tree.bindary.PrintUtil;

import java.util.Stack;

/**
 * 二叉树后序遍历
 */
public class PostOrderSearch implements TraversalStrategy {

    @Override
    public void recursionTraversal(BNode node) {
        if (node != null) {
            recursionTraversal(node.getLChild());
            recursionTraversal(node.getRChild());
            PrintUtil.visitRecrusion(node);
        }
    }

    /**
     * 循环遍历，按照定义执行   LRT
     * 这个实现 要点是 两次入栈，两次出栈
     * @param node
     */
    @Override
    public void loopTraversalDefinition(BNode node) {
        Stack<BNode> stackNode = new Stack<BNode>();
        BNode p = node;
        while (p != null || !stackNode.isEmpty()) {
            if (p != null) {
                p.setFlag(0);
                stackNode.push(p);          // 第一次入栈
                p = p.getLChild();
            }
            if (p == null) {
                p = stackNode.pop();
                if (p.getFlag() == 0) {     // 需要第二次入栈
                    p.setFlag(1);
                    stackNode.push(p);
                    p = p.getRChild();
                } else {
                    PrintUtil.visitLoop(p);
                    // 为了出栈
                    p = null;
                }
            }
        }
    }


    /**
     * 循环遍历，逆序操作      LRT   的逆序     TRL
     * @param node
     */
    @Override
    public void loopTraversalBackward(BNode node) {
        BNode p = node;
        Stack<BNode> stackPre = new Stack<BNode>();
        Stack<BNode> stackPost = new Stack<BNode>();
        while (p != null || !stackPost.isEmpty()) {
            if (p != null) {
                stackPre.push(p);
                stackPost.push(p);
                p = p.getRChild();
            } else {
                p = stackPost.pop();
                p = p.getLChild();
            }
        }
        while (!stackPre.isEmpty()) {
            p = stackPre.pop();
            PrintUtil.visitLoop(p);
        }
    }


}
