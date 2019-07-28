package com.learn.app.io.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-27 20:46
 */
public class SelectorTest {
    public static void main(String[] args) throws IOException {

        RandomAccessFile randomAccessFile = new RandomAccessFile(  "/tmp/a.txt", "rw");



        Selector selector = Selector.open();


    }
}
