package com.learn.app.io.nio.chatroom;

import java.io.*;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @desc 服务器
 * @author ji_fei
 * @date  2019-07-27 19:28
 */
public class ServerThreadAcceptSelector extends Thread {

    private Selector acceptSelector;

    private Selector clientSelector;


    public ServerThreadAcceptSelector(Selector selector, Selector clientSelector) {
       this.acceptSelector = selector;
       this.clientSelector = clientSelector;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (acceptSelector.select() > 0) {
                    Iterator<SelectionKey> selectionKeyList = acceptSelector.selectedKeys().iterator();
                    while (selectionKeyList.hasNext()) {
                        SelectionKey selectionKey = selectionKeyList.next();
                        // 每当有客户端连接过来，就将通道设置到 clientSelector
                        if (selectionKey.isAcceptable()) {
                            SocketChannel socketChannel = ((ServerSocketChannel)selectionKey.channel()).accept();
                            if (socketChannel != null) {
                                socketChannel.configureBlocking(false);
                                socketChannel.register(clientSelector, SelectionKey.OP_READ);
                            }
                        }
                        selectionKeyList.remove();
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
