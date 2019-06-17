package com.learn.algorithm.tree.bindary;

import com.learn.algorithm.tree.bindary.BNode;

public class PrintUtil {

    public static void visitRecrusion(BNode node) {
        System.out.print("递归遍历 -> node: " + node.getData().getKeyName() + " \t");
    }

    public static void visitLoop(BNode node) {
        System.out.print("循环遍历 -> node: " + node.getData().getKeyName() + " \t");
    }

    public static void println() {
        System.out.println("\n");
    }
}
