package com.learn.algorithm.tmp;

import java.io.File;

public class TreeTest {
    public static void listAll(File file, int depth) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            builder.append("\t");
        }
        System.out.println(builder.toString() + file.getName());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File item : files) {
                listAll(item, depth + 1);
            }
        }
    }
    public static void main(String[] args) {
        File folder = new File("D:\\");

        listAll(folder, 0);
    }
}
