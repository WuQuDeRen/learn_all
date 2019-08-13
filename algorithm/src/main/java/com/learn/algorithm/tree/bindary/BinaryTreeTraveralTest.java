package com.learn.algorithm.tree.bindary;

import com.learn.algorithm.tree.bindary.strategy.Context;
import com.learn.algorithm.tree.bindary.strategy.TrversalTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

public class BinaryTreeTraveralTest {

    private BNode root;

    public static BNode getBNode(String name) {
      return new BNode(name);
    }

    // # 代表 NULL
    private String seq = "AB#D##CE##F##";

    private Integer index = 0;

    // 手动创建二叉树
    @Before
    public void createBTree() {
        BNode root = getBNode("A");
        BNode b = getBNode("B");
        BNode c = getBNode("C");
        BNode d = getBNode("D");
        BNode e = getBNode("E");
        BNode f = getBNode("F");
        BNode g = getBNode("G");
        BNode h = getBNode("H");
        BNode i = getBNode("I");

        root.setLChild(b);
        root.setRChild(c);

        b.setLChild(d);
        d.setLChild(g);

        c.setLChild(e);
        c.setRChild(f);

        f.setLChild(h);

        h.setRChild(i);

        this.root = root;
        return;
    }

    // 先序遍历根据字符串自动创建二叉树
    @Before
    public void autoCreateBTree() {
        this.index = 0;
        BNode root = createBTree(null);
        this.root = root;
    }

    public BNode createBTree(BNode node) {
        String character = seq.charAt(index++) + "";
        if (StringUtils.equals(character, "#")) {
            return null;
        }
        node = getBNode(character);
        node.setLChild(createBTree(node.getLChild()));
        node.setRChild(createBTree(node.getRChild()));
        return node;
    }

    // 先序
    @Test
    public void testPreOrderSearch() {
        Context context = new Context(TrversalTypeEnum.TRL);
        context.recursionTraversal(root);
        PrintUtil.println();
        context.loopTraversalDefinition(root);
        PrintUtil.println();
        context.loopTraversalBackward(root);
    }

    // 中序
    @Test
    public void testMiddleOrderSearch() {
        Context context = new Context(TrversalTypeEnum.LTR);
        context.recursionTraversal(root);
        PrintUtil.println();
        context.loopTraversalDefinition(root);
        PrintUtil.println();
        context.loopTraversalBackward(root);
    }

    // 后序
    @Test
    public void testPostOrderSearch() {
        Context context = new Context(TrversalTypeEnum.LRT);
        context.recursionTraversal(root);
        PrintUtil.println();
        context.loopTraversalDefinition(root);
    }

    @Test
    public void testFloorSearch() {
        Context context = new Context(TrversalTypeEnum.HIERARCHY);
        context.loopTraversalHierarchy(root);
    }

    @Test
    public void testTreeNodeAmount() {
        root = new BNode("d");
        int amount = preCount(root, 0);

        System.out.println(amount);
    }

    // 计算总结点数
    public int preCount(BNode node, int count) {
        if (node != null) {
            count = count + 1;
            count = preCount(node.getLChild(), count);
            count = preCount(node.getRChild(), count);
            return count;
        }
        return count;
    }

    @Test
    public void testTreeHeight() {
        int height = preHeight(root, 0);
        System.out.println(height);
    }

    public int preHeight(BNode node, int height) {
        if (node != null) {
            height++;
            int lheight = preHeight(node.getLChild(), height);
            int rheight = preHeight(node.getRChild(), height);
            if (lheight < rheight) {
                return rheight;
            } else {
                return lheight;
            }
        }
        return height;
    }



}
