package com.learn.app.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.Selector;
import java.util.Random;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-25 18:19
 */
public class BufferTest {

    public static void main(String[] args) throws IOException {
        checkFileChannel();
        return;
    }

    public static void getRandomAccessFile() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("/Users/ji_fei/Downloads/data-20190705-013633.sql", "rw");
        FileChannel channel = aFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(48);
        int read = channel.read(buffer);
        while (read != -1) {
            System.out.println(read);
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char) + buffer.get());
            }
            buffer.clear();
            read = channel.read(buffer);
        }
        aFile.close();
    }



    public static void checkFileChannel() throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("/Users/ji_fei/Downloads/data-20190705-013633.sql", "rw");
        FileChannel fromChannel = fromFile.getChannel();
        RandomAccessFile toFile = new RandomAccessFile("/Users/ji_fei/Downloads/ddddddddd.sql", "rw");
        FileChannel toChannel = toFile.getChannel();


        long position = 0;
        long count = fromChannel.size();

        fromChannel.transferTo(position, count, toChannel);

        toChannel.transferFrom(toChannel, position, count);

    }

    public static void useSelector() throws IOException {
        Selector selector = Selector.open();
        RandomAccessFile fromFile = new RandomAccessFile("/Users/ji_fei/Downloads/data-20190705-013633.sql", "rw");
        FileChannel fileChannel = fromFile.getChannel();

        SelectableChannel channel;

    }

}
