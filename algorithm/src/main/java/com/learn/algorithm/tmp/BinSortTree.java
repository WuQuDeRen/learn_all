package com.learn.algorithm.tmp;

import com.google.common.collect.Lists;

import java.util.List;

public class BinSortTree {
    public static BinSortRecord tree = null;
    static {
        BinSortRecord root = new BinSortRecord(34);

        tree = root;

        BinSortRecord b2 = new BinSortRecord(18);
        root.setLChild(b2);

        BinSortRecord b3 = new BinSortRecord(76);
        root.setRChild(b3);

        BinSortRecord b4 = new BinSortRecord(13);
        b2.setLChild(b4);

        BinSortRecord b5 = new BinSortRecord(25);
        b2.setRChild(b5);

        BinSortRecord b6 = new BinSortRecord(20);
        b5.setLChild(b6);

        BinSortRecord b7 = new BinSortRecord(52);
        b3.setLChild(b7);
        BinSortRecord b8 = new BinSortRecord(82);
        b3.setRChild(b8);

        BinSortRecord b9 = new BinSortRecord(67);
        b7.setRChild(b9);

        BinSortRecord b10 = new BinSortRecord(58);
        b9.setLChild(b10);

        BinSortRecord b11 = new BinSortRecord(73);
        b9.setRChild(b11);

        BinSortRecord b12 = new BinSortRecord(91);
        b8.setRChild(b12);
    }

    // 二叉排序数查找
    public static BinSortRecord search(BinSortRecord root, Integer key) {
        if (root != null) {
            if (root.getKey().equals(key)) {
                return root;
            }
            if (root.getKey() > key) {
               return search(root.getLChild(), key);
            } else {
                return search(root.getRChild(), key);
            }
        }
        return null;
    }

    // 二叉排序数插入
    public static BinSortRecord insert(BinSortRecord root, Integer key) {
        if (root == null) {
            return  new BinSortRecord(key);
        }
        if (root.getKey() > key) {
            BinSortRecord record = insert(root.getLChild(), key);
            root.setLChild(record);
        } else {
            BinSortRecord record = insert(root.getRChild(), key);
            root.setRChild(record);
        }
        return root;
    }

    // 若删除成功，则返回 结点
    // 若删除失败，则返回 null
    public static BinSortRecord delete(BinSortRecord root, Integer key) {
        BinSortRecord p_father = null;
        BinSortRecord p = root;
        while (p != null && p.getKey() != key) {
            p_father = p;
            if (p.getKey() < key) {
                p = p.getRChild();
            } else {
                p = p.getLChild();
            }
        }
        if (p == null) {
            return null;
        }
        // 无左子树
        if (p.getLChild() == null) {
            // 删除根节点
            if (p_father == null) {
               return p.getRChild();
            } else {
                if (p_father.getRChild() == p) {
                    p_father.setRChild(p.getRChild());
                } else {
                    p_father.setLChild(p.getRChild());
                }
                return root;
            }
        }
        // 有左子树
        BinSortRecord s = p.getLChild();
        BinSortRecord s_father = p;
        while (s.getRChild() != null) {
            s_father = s;
            s = s.getRChild();
        }
        if (s_father == p) {
            s_father.setLChild(s.getLChild());
        } else {
            s_father.setRChild(s.getLChild());
        }
        p.setKey(s.getKey());
        return root;
    }


    public static void main(String[] args) {
        // 搜索
//        BinSortRecord search = search(tree, 73);
//        System.out.println(search);
        Integer[] arr = new Integer[]{34,18,76,52,13,67,82,58,73,16};
       // Integer[] arr = new Integer[]{34,76,54,91};
        // 插入
        BinSortRecord root = null;
        for (int i = 0; i < arr.length; i++) {
           root = insert(root, arr[i]);
        }
        System.out.println(root);

        BinSortRecord newRoot = delete(root, 34);
        System.out.println(newRoot);
    }

}
