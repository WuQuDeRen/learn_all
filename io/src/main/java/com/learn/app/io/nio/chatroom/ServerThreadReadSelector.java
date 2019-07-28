package com.learn.app.io.nio.chatroom;

import java.io.*;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-28 21:30
 */
public class ServerThreadReadSelector extends Thread {

    private Selector clientSelector;


    public ServerThreadReadSelector() {

    }

    public ServerThreadReadSelector(Selector clientSelector) {
        this.clientSelector = clientSelector;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (clientSelector.select(20) > 0) {
                    Iterator<SelectionKey> iterator = clientSelector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        if (selectionKey.isReadable()) {
                            SocketChannel channel = (SocketChannel) (selectionKey.channel());
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            buffer.rewind();
                            channel.read(buffer);
                            buffer.flip();
                            System.out.println(Charset.defaultCharset().newDecoder().decode(buffer).toString());
                            for (SelectionKey item : clientSelector.keys()) {
                                if (item.isValid()) {
                                    buffer.rewind();
                                    SocketChannel client = (SocketChannel) item.channel();
                                    if (channel != client) {
                                        client.write(buffer);
                                    }
                                }
                            }
                            buffer.clear();
                            iterator.remove();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}