package com.learn.algorithm.search.tree.sort;

import com.learn.algorithm.search.tree.BinaryTree;

public class BinSortTree {

    /**
     * 生成二叉排序树
     * @param tree
     * @param keyword
     * @return
     */
    public static BinaryTree generateBinarySortTree(BinaryTree tree, Integer keyword) {
        if (tree == null) {
            return new BinaryTree(keyword, null, null);
        }
        if (keyword >= tree.getData()) {
            BinaryTree tmp = generateBinarySortTree(tree.getRChild(), keyword);
            if (tree.getRChild() == null) {
                tree.setRChild(tmp);
            }
        } else {
            BinaryTree tmp = generateBinarySortTree(tree.getLChild(), keyword);
            if (tree.getLChild() == null) {
                tree.setLChild(tmp);
            }
        }
        return tree;
    }


    /**
     *  二叉排序数树搜索
     * @param tree
     * @param keyword
     * @return
     */
    public static BinaryTree search(BinaryTree tree, Integer keyword) {
        if (tree == null) {
            return null;
        }
        if (tree.getData().equals(keyword)) {
            return tree;
        }
        if (tree.getData() <= keyword) {
            return search(tree.getRChild(), keyword);
        } else {
            return search(tree.getLChild(), keyword);
        }
    }

    /**
     *
     * @return
     */
    public static void deleteNode(BinaryTree tree, Integer keyword) {
        BinaryTree parent = tree;
        BinaryTree child = tree;
        while (child != null && child.getData() != keyword) {
            parent = child;
            if (child.getData() < keyword) {
                child = child.getRChild();
            } else {
                child = child.getLChild();
            }
            if (child.getData() == keyword) {
                // 无左节点
                if (child.getLChild() == null) {
                    parent.setLChild(child.getRChild());
                }
                // 有左节点
                if (child.getLChild() != null) {
                    BinaryTree current = child.getLChild();
                    while (current.getRChild() != null) {
                        current = current.getRChild();
                    }
                    parent.setRChild(current);
                    current.setRChild(child.getRChild());
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{34,18,76,52,13,67,82,58,73,16};
        // 创建
        BinaryTree tree = null;
        for (int i = 0; i < arr.length; i++) {
            tree = generateBinarySortTree(tree, arr[i]);
        }
        // 搜索
        BinaryTree result = search(tree, 76);
        System.out.println(result);

        // 删除
        deleteNode(tree, 16);
        System.out.println(tree);
    }
}
