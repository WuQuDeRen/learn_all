package com.learn.app.io.nio;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-28 01:12
 */
public class FileChannelTest {

    private static String path = "/tmp/";

    @Test
    public void testTransfer() throws IOException {
        RandomAccessFile sourceFile = new RandomAccessFile(path + "a.txt", "rw");
        FileChannel sourceChannel = sourceFile.getChannel();

        RandomAccessFile targetFile = new RandomAccessFile(path + "b.txt", "rw");
        FileChannel targetChannel = sourceFile.getChannel();

        targetChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

        sourceChannel.close();
        targetChannel.close();
    }
}
