package com.learn.app.io.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-25 18:19
 */
public class BufferTest {

    private static String path = "/tmp/";

    public static void main(String[] args) throws IOException {

        RandomAccessFile randomAccessFile = new RandomAccessFile(path + "a.txt", "rw");

        FileChannel channel = randomAccessFile.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        int len = channel.read(buffer);
        while (len != -1) {
            // 写模式切换到读模式，在需要读模式的时候，一定要调用这个方法，进行模式切换，否则读取数据会内容异常
            buffer.flip();
            while (buffer.hasRemaining()) {
                byte current = buffer.get();
                System.out.print((char)current);
            }
            // 在每次读取完毕后，一定要 调用 clear 或 compact 方法进行缓存去的清空处理，不然会报错
            buffer.clear();
            len = channel.read(buffer);
        }

        return;
    }



}
